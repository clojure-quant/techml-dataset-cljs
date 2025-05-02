(ns cquant.transit.techml
  (:require
   [tech.v3.dataset :as ds]
   [cognitect.transit :as t]
   [tech.v3.dataset.impl.dataset :as ds-impl]
   ;[tech.v3.datatype.datetime :as dtype-dt]
   #_[java.time :refer [LocalDate Instant]]
   [tech.v3.libs.clj-transit :refer [dataset->data data->dataset]]
   [transit.handler :refer [add-transit-io-handlers!]])
  (:import
   [tech.v3.dataset.impl.dataset Dataset]))

(def transit-write-handlers {Dataset (t/write-handler "tech.v3.dataset" dataset->data)
                             org.roaringbitmap.RoaringBitmap (t/write-handler "org.roaringbitmap.RoaringBitmap" vec)})

(def transit-read-handlers {"tech.v3.dataset" (t/read-handler data->dataset)
                            "org.roaringbitmap.RoaringBitmap" (t/read-handler
                                                               (fn [v] (org.roaringbitmap.RoaringBitmap/bitmapOf (int-array v))))})

(defn add-techml-dataset-handlers! []
  (add-transit-io-handlers!
   ;(ds/transit-read-handler-map)
   ;(ds/transit-write-handler-map)
   transit-read-handlers
   transit-write-handlers))

#_(defn add-java-time-handlers!
    "Add handlers for java.time.LocalDate and java.time.Instant"
    []
    (add-transit-io-handlers! LocalDate "java.time.LocalDate"
                              dtype-dt/epoch-days->local-date
                              dtype-dt/local-date->epoch-days)
    (add-transit-io-handlers! Instant "java.time.Instant"
                              dtype-dt/epoch-milliseconds->instant
                              dtype-dt/instant->epoch-milliseconds))

