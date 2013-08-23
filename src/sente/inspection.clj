(ns sente.inspection
  (:require [clojure.pprint :as pp]))

(defn pprint-code
  "Print code readably."
  [code]
  (pp/with-pprint-dispatch pp/code-dispatch
    (pp/pprint code)))
