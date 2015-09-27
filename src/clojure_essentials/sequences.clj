;reload code in the repl with (use 'clojure-essentials.sequences :reload) every time you make changes
(ns clojure-essentials.sequences)
; a very important clojure abtraction is the sequence seq. 
; A seq represents a logical list and it exposes 3 basic functions
(first [1 2 3])
(rest [1 2 3])
(cons 0 [1 2 3])

;drop, take, cycle, interleave, partition, interpose, map,  reduce
(take 20 (map #(* % 2) (cycle (range 1 5))))
; using threading macro
(->> (range 1 5) cycle (map #(* % 2)) (take 20))

; infinite lazy sequence
(defn even-numbers
  ([] (even-numbers 0))
  ([n] (cons n (lazy-seq (even-numbers (+ n 2))))))
