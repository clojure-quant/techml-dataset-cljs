(ns cquant.transit
  (:require
   [tech.v3.dataset :as ds]
   [tech.v3.datatype.datetime :as dtype-dt]
   [java.time :refer [LocalDate Instant]]
   [cognitect.transit :as t]))

(defonce write-handlers* (atom (ds/transit-write-handler-map)))
(defonce read-handlers* (atom (ds/transit-read-handler-map)))

(defn add-transit-io-handlers!
  [datatype tag read-fn write-fn]
  (swap! write-handlers* assoc datatype (t/write-handler (constantly tag) write-fn))
  (swap! read-handlers* assoc tag read-fn))

(defn add-java-time-handlers!
  "Add handlers for java.time.LocalDate and java.time.Instant"
  []
  (add-transit-io-handlers! LocalDate "java.time.LocalDate"
                            dtype-dt/epoch-days->local-date
                            dtype-dt/local-date->epoch-days)
  (add-transit-io-handlers! Instant "java.time.Instant"
                            dtype-dt/epoch-milliseconds->instant
                            dtype-dt/instant->epoch-milliseconds))

(add-java-time-handlers!)

(defn write-transit [data]
  (let [writer (t/writer :json {:handlers @write-handlers*})]
    (t/write writer data)))

(defn read-transit [data]
  (let [reader (t/reader :json {:handlers @read-handlers*})]
    (t/read reader data)))