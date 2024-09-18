(ns clojure-quant.tmlds
  (:require
    [tech.v3.dataset :as ds]
    [tech.v3.libs.transit :as tech-transit]
    [tech.v3.io :as io]))


(defn ds->transit-json-file
  [ds fname]
  (with-open [outs (io/output-stream! fname)]
    (tech-transit/dataset->transit ds outs :json tech-transit/java-time-write-handlers)))
