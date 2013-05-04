(ns ^{:doc "Convenience functions macros to make specific code
            situations more concise."}
  sente.concision)

(defmacro let-map
  "Cleans up the map destructuring let forms for cases
  like the following:
  (let [{:keys [x y] :as foo} {:x 1 :y 2}
        {:keys [hello world]} {:hello 9000 :world 42}]
    ...)

  ;; Example Usage:
  (let-map
    :keys [[x y] {:x 1 :y 2}
           [a b] {:a 10 :b 20}]
    [x b])

  ;=> [1 20]"
  [kw bindings & body]
  `(let ~(apply (comp vec concat)
                (map (fn [[k# v#]] [{:keys k#} v#])
                     (apply hash-map bindings)))
     ~@body))
