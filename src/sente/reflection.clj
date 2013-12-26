(ns sente.reflection
  (:require [clojure.pprint :refer [print-table]]
            [clojure.reflect :refer [reflect]]))

(defn print-methods [o]
  (->> o
       reflect
       :members
       (filter :exception-types)
       (sort-by :name)
       print-table))
