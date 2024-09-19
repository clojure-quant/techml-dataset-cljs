(ns clojure-quant.tmlds
  (:require
   [nano-id.core :refer [nano-id]]
   ;[tech.v3.libs.transit :as tech-transit]
   [tech.v3.libs.clj-transit :as tech-transit]
   [tech.v3.io :as io]))

(defn ds->transit-json-file
  [ds fname]
  (with-open [outs (io/output-stream! fname)]
    (tech-transit/dataset->transit ds outs :json tech-transit/java-time-write-handlers)))

(defonce ds-cache (atom {}))

(defn serve-once 
  ([ds]
   (serve-once (nano-id 5)))
  ([ds id]
   (swap! ds-cache assoc id ds)))
    

(defn load-once [id]
  (let [ds (get @ds-cache id)]
    (println "ds: " ds)
    (swap! ds-cache dissoc id)
    ds))
