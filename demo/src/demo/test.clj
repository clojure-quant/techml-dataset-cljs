(ns demo.test
 (:require
  [tick.core :as t]
  [tech.v3.dataset :as ds]
  [cquant.tmlds :as qds]))

(defn demo-ds [n]
  (ds/->dataset
   {:a (range n)
    :b (take n (cycle [:a :b :c]))
    :c (take n (cycle ["one" "two" "three"]))}))

(-> (demo-ds 100)
    (qds/ds->transit-json-file "target/webly/public/dstest.transit-json"))


(def dt (t/instant))
;; => #'demo.test/dt
dt
;; => #time/instant "2024-11-13T02:30:04.750623060Z"

(qds/ds->transit-json-file dt ".webly/dt.transit-json")

(qds/transit-json-file->ds ".webly/dt.transit-json")

