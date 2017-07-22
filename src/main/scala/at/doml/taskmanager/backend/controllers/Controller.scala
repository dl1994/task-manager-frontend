package at.doml.taskmanager.backend.controllers

import at.doml.taskmanager.Config
import at.doml.taskmanager.backend.Response
import at.doml.taskmanager.backend.models.resp.Aliases.Empty
import org.scalajs.dom.XMLHttpRequest
import org.scalajs.dom.ext.Ajax
import scala.concurrent.{ExecutionContext, Future}
import scala.scalajs.js
import scala.scalajs.js.JSON

trait Controller {

    private final implicit val context: ExecutionContext = ExecutionContext.Implicits.global

    protected abstract class HttpRequestBuilder[T](url: String) {

        protected var requestBody: String = _
        protected var withCredentials: Boolean = true
        protected var headers: Map[String, String] = Map.empty

        def as[R](out: Class[R]): Response[R] = {
            new Response(
                this.performRequest().map(r => (this.getResponse(out, r), r.status))
            )
        }

        private def getResponse[R](out: Class[R], r: XMLHttpRequest): R = {
            if (out == Empty) {
                null.asInstanceOf[R]
            } else {
                JSON.parse(r.response.asInstanceOf[String]).asInstanceOf[R]
            }
        }

        def asListOf[R](out: Class[R]): Response[js.Array[R]] = {
            new Response(
                this.performRequest()
                    .map(r => (JSON.parse(r.response.asInstanceOf[String]).asInstanceOf[js.Array[R]], r.status))
            )
        }

        def withJson(req: js.Object): HttpRequestBuilder[T] = {
            this.headers = Map("Content-Type" -> "application/json; charset=utf-8")
            this.requestBody = JSON.stringify(req)
            this
        }

        def withFromData(req: String): HttpRequestBuilder[T] = {
            this.headers = Map("Content-Type" -> "application/x-www-form-urlencoded; charset=utf-8")
            this.requestBody = req
            this
        }

        def useCredentials(useCredentials: Boolean): HttpRequestBuilder[T] = {
            this.withCredentials = useCredentials
            this
        }

        def performRequest(): Future[XMLHttpRequest]
    }

    private class GetHttpRequest[T](url: String) extends HttpRequestBuilder[T](url) {

        override def performRequest(): Future[XMLHttpRequest] =
            Ajax.get(url, data = requestBody, withCredentials = withCredentials, headers = headers)
    }

    private class PostHttpRequest[T](url: String) extends HttpRequestBuilder[T](url) {

        override def performRequest(): Future[XMLHttpRequest] =
            Ajax.post(url, data = requestBody, withCredentials = withCredentials, headers = headers)
    }

    private class PutHttpRequest[T](url: String) extends HttpRequestBuilder[T](url) {

        override def performRequest(): Future[XMLHttpRequest] =
            Ajax.put(url, data = requestBody, withCredentials = withCredentials, headers = headers)
    }

    private class DeleteHttpRequest[T](url: String) extends HttpRequestBuilder[T](url) {

        override def performRequest(): Future[XMLHttpRequest] =
            Ajax.delete(url, data = requestBody, withCredentials = withCredentials, headers = headers)
    }

    protected final def get(url: String)(implicit prefix: String): HttpRequestBuilder[XMLHttpRequest] =
        new GetHttpRequest(Config.BACKEND_PATH + prefix + url)

    protected final def post(url: String)(implicit prefix: String): HttpRequestBuilder[XMLHttpRequest] =
        new PostHttpRequest(Config.BACKEND_PATH + prefix + url)

    protected final def put(url: String)(implicit prefix: String): HttpRequestBuilder[XMLHttpRequest] =
        new PutHttpRequest(Config.BACKEND_PATH + prefix + url)

    protected final def delete(url: String)(implicit prefix: String): HttpRequestBuilder[XMLHttpRequest] =
        new DeleteHttpRequest(Config.BACKEND_PATH + prefix + url)

    protected final def optParams(params: (String, Any)*): String = {
        val paramsString = params.toStream
            .filter(_._2 != null)
            .map(t => t._1 + '=' + t._2)
            .toArray
            .mkString("&")
        if (!paramsString.isEmpty) {
            '?' + paramsString
        } else {
            paramsString
        }
    }
}
