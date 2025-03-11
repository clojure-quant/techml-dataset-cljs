(ns cquant.tmlds
  (:require
   [tech.v3.dataset.column :as col]
   [tablecloth.api :as tc]))

(defn clone-ds [d]
  (->> (tc/column-names d)
       (map (fn [col-n]
              [col-n (col/clone (get d col-n))]))
       (into {})
       (tc/dataset)))

