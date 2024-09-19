(ns cquant.tmlds
  (:require
   [promesa.core :as p]
   [tech.v3.libs.cljs-ajax :as techml-ajax]
   [cquant.text :refer [text]]
   ))

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


;; perhaps we want to use cols->str

(defn ds->txt [ds]
  (with-out-str
    (println ds)))

(defn ds-txt 
  "renders a techml dataset as text in the browser"
  [ds]
  [text (ds->txt ds)])