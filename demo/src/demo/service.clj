(ns demo.service
  (:require
   [tech.v3.dataset :as ds]
   [transit.io :refer [encode]]
   [dali.store.cache :refer [store-once]]
   ))

(defn demo-ds [n]
  (ds/->dataset
   {:a (range n)
    :b (take n (cycle [:a :b :c]))
    :c (take n (cycle ["one" "two" "three"]))}))

(defn spit-ds [ds filename]
  (spit filename (encode ds)))

(-> (demo-ds 100)
    (spit-ds ".gorilla/public/small.json"))
 
(-> (demo-ds 10000)
    (spit-ds ".gorilla/public/10k.json"))

(-> (demo-ds 100000)
    (spit-ds ".gorilla/public/100k.json"))

(-> (demo-ds 1000000)
    (spit-ds ".gorilla/public/1000k.json"))


(defn serve-once-test []
   (println "generating tml ds id 27 28 29 (store-once) ..")
   (store-once  (demo-ds 100) "27")
   (store-once  (demo-ds 100) "28")
   (store-once  (demo-ds 100) "29"))

