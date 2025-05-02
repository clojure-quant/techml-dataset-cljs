(ns demo.standalone
  (:require 
    [transit.type.tick :refer [add-tick-handlers!]]
    [cquant.transit.techml :refer [add-techml-dataset-handlers!]]))

(add-tick-handlers!)

(add-techml-dataset-handlers!)