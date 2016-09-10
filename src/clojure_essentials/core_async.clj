;reload code in the repl with (use 'clojure-essentials.core-async :reload) every time you make changes

;(require '[clojure.core.async :as async :refer [<! >! <!! timeout chan alts! alt! go buffer close! thread]])

(ns clojure-essentials.core-async
	(:require [clojure.core.async 
		:as a 
		:refer [>! <! >!! <!! go chan buffer close! thread alts! alts!! timeout]]))

(defn race
	[c1 c2]
	(go (while true
        (let [[v ch] (alts! [c1 c2])]
          (println "Winner is .. " v)))))

