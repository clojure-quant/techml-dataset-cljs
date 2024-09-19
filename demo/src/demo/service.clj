(ns demo.service
  (:require
   [tech.v3.dataset :as ds]
   [cquant.tmlds :as qds]))

(defn demo-ds [n]
  (ds/->dataset
   {:a (range n)
    :b (take n (cycle [:a :b :c]))
    :c (take n (cycle ["one" "two" "three"]))}))


(defn a-seed []
  [1 2 3 4 5])


(-> (demo-ds 100)
    (qds/ds->transit-json-file "target/webly/public/small.json"))
 
(-> (demo-ds 10000)
    (qds/ds->transit-json-file "target/webly/public/10k.json"))

(-> (demo-ds 100000)
    (qds/ds->transit-json-file "target/webly/public/100k.json"))

(-> (demo-ds 1000000)
    (qds/ds->transit-json-file "target/webly/public/1000k.json"))


(defn serve-once-test []
   (println "generating tml ds id 27 28 29 (serve once) ..")
   (qds/serve-once  (demo-ds 100) "27")
   (qds/serve-once  (demo-ds 100) "28")
   (qds/serve-once  (demo-ds 100) "29"))

