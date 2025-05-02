(ns cquant.config
  (:require
   [cquant.transit.techml :refer [add-techml-dataset-handlers!]]))

(defn start-techml-transit [_config]
  (println "starting techml-transit")
  (add-techml-dataset-handlers!)
  nil ; we dont wait on this.
  )