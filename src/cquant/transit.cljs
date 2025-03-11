(ns cquant.transit
  (:require
   [transit.type.techml :refer [add-techml-dataset-handlers!]]))


(defn add-dataset-transit-encoding []
  (println "adding dataset transit encoding..")
  (add-techml-dataset-handlers!))


; this is the side effect that we wnat to happen.
(add-dataset-transit-encoding)