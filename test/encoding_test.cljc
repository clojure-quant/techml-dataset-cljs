(ns encoding-test
  (:require
   [clojure.test :refer [deftest is testing]]
   [tick.core :as t]
   [tech.v3.dataset :as ds]
   [transit.io :refer [encode decode]]
   [transit.type.tick :refer [add-tick-handlers!]]
   [cquant.transit.techml :refer [add-techml-dataset-handlers!]]))

(def data
  {:a 1 :b "b"
   :i (t/instant)
   :d (t/date)
   :dt (t/date-time)
   :date-zoned (t/zoned-date-time)})

(add-tick-handlers!)

(deftest transit-encode-decode-tick-test []
  (is (= data
         (-> data encode decode))))


(defn demo-ds [n]
  (ds/->dataset
   {:a (range n)
    :b (take n (cycle [:a :b :c]))
    :c (take n (cycle ["one" "two" "three"]))}))

(def data-ds (demo-ds 100))


(add-techml-dataset-handlers!)

(deftest transit-encode-decode-dataset-test []
  (is (= data-ds
         (-> data-ds encode decode))))

(defn demo-tick-ds [n]
  (ds/->dataset
   {:a (range n)
    :b (take n (cycle [:a :b :c]))
    :c (take n (cycle [(t/instant "2025-03-10T20:11:26.236Z")]))}))

(def data-tick-ds (demo-tick-ds 100))

(deftest transit-encode-decode-tick-dataset-test []
  (is (= data-tick-ds
         (-> data-tick-ds encode decode))))
