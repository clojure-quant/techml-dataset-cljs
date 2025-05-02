(ns cquant.transit.techml
  (:require
   [cognitect.transit :as t]
   [tech.v3.dataset :as ds]
   [tech.v3.datatype.bitmap :refer [->bitmap]]
   ;[tech.v3.datatype.datetime :as dtype-dt]
   #_[java.time :refer [LocalDate Instant]]
   [transit.handler :refer [add-transit-io-handlers!]]))

#_(def write-handlers {org.roaringbitmap.RoaringBitmap
                       (t/write-handler (constantly "org.roaringbitmap.RoaringBitmap") vec)})

(def read-handlers {"org.roaringbitmap.RoaringBitmap"
                    (t/read-handler
                     (fn [v]
                       (->bitmap v)))})

(defn add-techml-dataset-handlers! []
  (println "adding dataset transit encoding..")
  (add-transit-io-handlers!
   #_(ds/transit-read-handler-map)
   (merge
    (ds/transit-read-handler-map)
    read-handlers)
   (ds/transit-write-handler-map)))

#_(defn add-java-time-handlers!
    "Add handlers for java.time.LocalDate and java.time.Instant"
    []
    (add-transit-io-handlers! LocalDate "java.time.LocalDate"
                              dtype-dt/epoch-days->local-date
                              dtype-dt/local-date->epoch-days)
    (add-transit-io-handlers! Instant "java.time.Instant"
                              dtype-dt/epoch-milliseconds->instant
                              dtype-dt/instant->epoch-milliseconds))
