(ns demo.test
 (:require
 [tech.v3.dataset :as ds]
 [clojure-quant.tmlds :as qds]))



(defn demo-ds [n]
  (ds/->dataset
   {:a (range n)
    :b (take n (cycle [:a :b :c]))
    :c (take n (cycle ["one" "two" "three"]))}))

(-> (demo-ds 100)
    (qds/ds->transit-json-file "target/webly/public/dstest.transit-json"))