(ns cquant.tmlds
  (:require
   [cquant.text :refer [text]]))

;; perhaps we want to use cols->str

(defn ds->txt [ds]
  (with-out-str
    (println ds)))

(defn ds-txt
  "renders a techml dataset as text in the browser"
  [ds]
  [text (ds->txt ds)])