(ns sente.readability)

(defn range-inc
  ([] (range-inc 0 Double/POSITIVE_INFINITY 1))
  ([end] (range-inc 0 end 1))
  ([start end] (range-inc start end 1))
  ([start end step] (range start (inc end) step)))
