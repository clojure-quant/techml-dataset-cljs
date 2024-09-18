(ns demo.page
  (:require
   [tech.v3.dataset :as ds]
   [demo.text :refer [text]]
   ))

(def ds
  (ds/->dataset
   {:a (range 100)
    :b (take 100 (cycle [:a :b :c]))
    :c (take 100 (cycle ["one" "two" "three"]))}))

(def ds-txt
  (with-out-str
    (println ds)))


(defn show-page []
  [:div
   [:p.text-big.text-blue-900.text-bold " demo .."]

   [:div.bg-green-500.m-5.p-5
    [text ds-txt]
    ]])

(defn page [_route]
  [show-page])

