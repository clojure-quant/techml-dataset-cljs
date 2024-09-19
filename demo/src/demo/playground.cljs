(ns demo.playground
  (:require
   [tech.v3.dataset :as tmlds]))

;; nrepl cljs port is 8002


(def ds
  (tmlds/->dataset
   {:a (range 100)
    :b (take 100 (cycle [:a :b :c]))
    :c (take 100 (cycle ["one" "two" "three"]))}))

(tmlds/columns ds)

(tmlds/rows ds)

(-> (tmlds/rows ds)
    (get 1)
    :a)

(-> (tmlds/rows ds)
    type
    )

