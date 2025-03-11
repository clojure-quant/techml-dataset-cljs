(ns demo.test
  (:require
   [tick.core :as t]
   [tech.v3.dataset :as ds]
   [tech.v3.datatype.datetime :as dtype-dt]
   [transit.io :refer [encode]]))

(defn demo-ds [n]
  (ds/->dataset
   {:a (range n)
    :b (take n (cycle [:a :b :c]))
    :c (take n (cycle ["one" "two" "three"]))}))

(defn spit-ds [ds filename]
  (spit filename (encode ds)))

(-> (demo-ds 100)
    (spit-ds "target/webly/public/dstest.transit-json"))


(def dt (t/instant))
;; => #'demo.test/dt
dt
;; => #time/instant "2024-11-13T02:30:04.750623060Z"

(spit-ds dt ".webly/dt.transit-json")

(spit-ds ".webly/dt.transit-json")




(defn ds->transit-json-file2
  [ds fname]
  (with-open [outs (io/output-stream! fname)]
    (tech-transit/dataset->transit ds outs)))

(defn transit-json-file->ds2
  [fname]
  (with-open [ins (io/input-stream fname)]
    (tech-transit/transit->dataset ins)))

(def dsdt
  (ds/->dataset
   {:dt [(t/instant)]}))

dsdt
;; => _unnamed [1 1]:
;;    
;;    |                         :dt |
;;    |-----------------------------|
;;    | 2024-11-13T02:42:49.219519Z |


(spit-ds dsdt ".webly/dsdt2.transit-json")




  (defn master-ds
  []
  (ds/->dataset {:a (mapv double (range 5))
                 :b (repeat 5 :a)
                 :c (repeat 5 "hey")
                 :d (repeat 5 {:a 1 :b 2})
                 :e (repeat 4 [1 2 3])
                 :f (repeat 5 (dtype-dt/local-date))
                 :g (repeat 5 (dtype-dt/instant))
                 :h [true false true true false]
                 :i (repeat 5 "text")
                 :j [1 nil 2 nil 3]}
                {:parser-fn {:i :text}}))

(master-ds)
;; => _unnamed [5 10]:
;;    
;;    |      :e |                          :g |  :c | :j |    :h | :b |           :d |         :f |   :i |  :a |
;;    |---------|-----------------------------|-----|---:|-------|----|--------------|------------|------|----:|
;;    | [1 2 3] | 2024-11-13T12:12:01.774336Z | hey |  1 |  true | :a | {:a 1, :b 2} | 2024-11-13 | text | 0.0 |
;;    | [1 2 3] | 2024-11-13T12:12:01.774336Z | hey |    | false | :a | {:a 1, :b 2} | 2024-11-13 | text | 1.0 |
;;    | [1 2 3] | 2024-11-13T12:12:01.774336Z | hey |  2 |  true | :a | {:a 1, :b 2} | 2024-11-13 | text | 2.0 |
;;    | [1 2 3] | 2024-11-13T12:12:01.774336Z | hey |    |  true | :a | {:a 1, :b 2} | 2024-11-13 | text | 3.0 |
;;    |         | 2024-11-13T12:12:01.774336Z | hey |  3 | false | :a | {:a 1, :b 2} | 2024-11-13 | text | 4.0 |



(-> (master-ds)
    (tech-transit/dataset->transit-str)
    (tech-transit/transit-str->dataset))
;; => _unnamed [5 10]:
;;    
;;    |      :e |                          :g |  :c | :j |    :h | :b |           :d |         :f |   :i |  :a |
;;    |---------|-----------------------------|-----|---:|-------|----|--------------|------------|------|----:|
;;    | [1 2 3] | 1970-01-21T00:58:19.924669Z | hey |  1 |  true | :a | {:a 1, :b 2} | 2024-11-13 | text | 0.0 |
;;    | [1 2 3] | 1970-01-21T00:58:19.924669Z | hey |    | false | :a | {:a 1, :b 2} | 2024-11-13 | text | 1.0 |
;;    | [1 2 3] | 1970-01-21T00:58:19.924669Z | hey |  2 |  true | :a | {:a 1, :b 2} | 2024-11-13 | text | 2.0 |
;;    | [1 2 3] | 1970-01-21T00:58:19.924669Z | hey |    |  true | :a | {:a 1, :b 2} | 2024-11-13 | text | 3.0 |
;;    |         | 1970-01-21T00:58:19.924669Z | hey |  3 | false | :a | {:a 1, :b 2} | 2024-11-13 | text | 4.0 |

