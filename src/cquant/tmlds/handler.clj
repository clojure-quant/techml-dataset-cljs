(ns cquant.tmlds.handler
  (:require
   [ring.util.response :as response]
   [ring.middleware.params :refer [wrap-params]]
   [ring.middleware.keyword-params :refer [wrap-keyword-params]]
   [modular.webserver.middleware.exception :refer [wrap-fallback-exception]]
   ;muuntaja.middleware :refer [wrap-format]] ; 30x faster than ring.middleware.format
   [ring.middleware.gzip :refer [wrap-gzip]]
   [tech.v3.libs.muuntaja :refer [wrap-format-java-time]]
   [clojure-quant.tmlds :refer [load-once]]))

(defn ds-handler [{:keys [route-params] :as _req} ]
  (let [id (:id route-params)
        _ (println "DS HANDLER ID: " id)
        ds (when id (load-once id))]
    (if ds
      (response/response ds)
      (response/not-found nil))))

(def ds-handler-wrapped
  (-> ds-handler ; middlewares execute from bottom -> up
      ;(wrap-anti-forgery)
      ;(wrap-defaults api-defaults)
      (wrap-keyword-params)
      (wrap-params)
      (wrap-format-java-time) 
      ;(wrap-format muuntaja)
      ;(wrap-json-response)
      (wrap-gzip)
      (wrap-fallback-exception)
      ))

