(ns sente.familiar)

(defn includes? [s & strings]
  (boolean (some true? (map #(.contains s %) strings))))
