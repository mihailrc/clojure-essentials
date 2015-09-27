;reload code in the repl with (use 'clojure-essentials.concurrency :reload) every time you make changes
(ns clojure-essentials.concurrency)

;============= ATOMS ===============

;atoms implement synchronous, uncoordinated, atomic compare and set modifications
(def checking (atom {:name "Checking account" :balance 100}))
;dereference the value
@checking
;update atom value with
(swap! checking update-in [:balance] + 15)

;operations that modify atom state block until completion 
(defn update-account
	[account amount]
	(swap! account (fn [v]
				 (when (pos? amount) (Thread/sleep (* amount 100)))
				 (println "Adding " amount)		
				 (update-in v [:balance] + amount)))
	@account)
;put it in a future so I don't block current thread
(future (update-account checking 5))
;each modification is isolated. No threads will see the atom in an inconsistent state
;dereferencing an atom returns immediately
@checking

;swap! retries until it succeeds
;NOTE: functions inside swap should be pure functions and side effect becuase one cannot control
;how many times swap! will be invoked. 
(do
	(future (update-account checking 10))
	(future (update-account checking 3)))

;to set the atom to a known value
(reset! checking {:name "Checking account" :balance 100})
;watches are functions that are called when the state changes
(defn echo [refName key oldVal newVal]
	(println "I am watching you ... KEY:" key "OLD:" oldVal " NEW:" newVal))

;(add-watch checking :echo echo)
;(remove-watch checking :echo)

;challenge  - use watches to record history of changes to an atom

;validators
(defn hasMoney
	[account]
	(pos? (:balance account)))
(set-validator! checking hasMoney)

;============= REFS ===============
;if you need to coordinate updates across entities use refs. Refs use STM to preserve consistency
(defn transfer
	[from to amount]
	(dosync
		(alter from update-in [:balance] - amount)
		(alter to update-in [:balance] + amount)))

