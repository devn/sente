(ns ^{:doc "Tools to better understand what your code is doing."}
  sente.understanding
  (:require [clojure.main :refer [repl repl-read]]))

(defn- readr [prompt exit-code]
  (let [input (clojure.main/repl-read prompt exit-code)]
    (if (= input :c)
      exit-code
      input)))

(defmacro ^:private local-context []
  (let [symbols (keys &env)]
    (zipmap (map (fn [sym] `(quote ~sym)) symbols) symbols)))

(defn- contextual-eval [ctx expr]
  (eval
   `(let [~@(mapcat (fn [[k v]] [k `'~v]) ctx)]
      ~expr)))

;; Stolen liberally from Chris Houser and Michael Fogus' most
;; excellent Clojure book: The Joy of Clojure
(defmacro break
  "Put a REPL in your REPL to inspect locals.

  ;; Usage:
  (let [x 1] (break) (inc x))
  debug=> x
  1
  debug=> :c
  => 1"
  []
  `(clojure.main/repl
    :prompt #(do (print "debug=> ")
                 (flush))
    :read readr
    :eval (partial contextual-eval (local-context))))
