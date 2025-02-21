(ns cquant.tmlds.handler
  (:require
   [ring.util.response :as response]
   ;[ring.middleware.params :refer [wrap-params]]
   ;[ring.middleware.keyword-params :refer [wrap-keyword-params]]
   ;[modular.webserver.middleware.exception :refer [wrap-fallback-exception]]
   ;muuntaja.middleware :refer [wrap-format]] ; 30x faster than ring.middleware.format
   ;[ring.middleware.gzip :refer [wrap-gzip]]
   ;[tech.v3.libs.muuntaja :refer [wrap-format-java-time]]
   [cquant.tmlds :refer [load-once]]))

(defn ds-handler [{:keys [#_route-params path-params] :as req}]
  (let [;id (:id route-params) ; bidi
        id (:id path-params) ; reitit
        ;_ (println "DS HANDLER ID: " id)
        ;_ (println "DS HANDLER path-params:" path-params)
        ds (when id (load-once id))]
    (if ds
      (response/response ds)
      (response/not-found nil))))

#_(def ds-handler-wrapped
  (-> ds-handler ; middlewares execute from bottom -> up
      ;(wrap-anti-forgery)
      ;(wrap-defaults api-defaults)
      (wrap-keyword-params)
      (wrap-params)
      (wrap-format-java-time)
      ;(wrap-format muuntaja)
      ;(wrap-json-response)
      (wrap-gzip)
      (wrap-fallback-exception)))

