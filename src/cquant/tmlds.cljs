(ns cquant.tmlds
  (:require
   [promesa.core :as p]
   [tech.v3.libs.cljs-ajax :as techml-ajax]))

(defn wrap-promise
  [AJAX-TYPE url params]
  (p/create
   (fn [resolve reject]
     (AJAX-TYPE url
                (merge params
                       {:handler (fn [response]
                                   (resolve response))
                        :error-handler (fn [error]
                                         (reject error))})))))

(defn GET
  ([url] (GET url {}))
  ([url params] (wrap-promise techml-ajax/GET url params)))

(defn POST
  ([url] (POST url {}))
  ([url params] (wrap-promise techml-ajax/POST url params)))
