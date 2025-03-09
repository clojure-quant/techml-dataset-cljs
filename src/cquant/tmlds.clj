(ns cquant.tmlds
  (:require
   [nano-id.core :refer [nano-id]]
   [tech.v3.libs.clj-transit :as tech-transit]
   [tech.v3.io :as io]
   [tech.v3.dataset.column :as col]
   [tablecloth.api :as tc]))

(defn clone-ds [d]
  (->> (tc/column-names d)
       (map (fn [col-n]
              [col-n (col/clone (get d col-n))]))
       (into {})
       (tc/dataset)))

(defn ds->transit-json-file
  [ds fname]
  (with-open [outs (io/output-stream! fname)]
    (tech-transit/dataset->transit ds outs :json tech-transit/java-time-write-handlers)))

(defn transit-json-file->ds
  [fname]
  (with-open [ins (io/input-stream fname)]
    (tech-transit/transit->dataset ins :json tech-transit/java-time-read-handlers)))

(defonce ds-cache (atom {}))

(defn serve-once
  ([ds]
   (serve-once (nano-id 5)))
  ([ds id]
   (swap! ds-cache assoc id ds)))

(defn load-once [id]
  (let [ds (get @ds-cache id)]
    (println "cquant.tmlds/load-once ds: " ds)
    (swap! ds-cache dissoc id)
    ds))
