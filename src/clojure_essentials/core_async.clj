;reload code in the repl with (use 'clojure-essentials.core-async :reload) every time you make changes
(ns clojure-essentials.core-async
	(:require [clojure.core.async 
		:as a 
		:refer [>! <! >!! <!! go chan buffer close! thread alts! alts!! timeout]]))
