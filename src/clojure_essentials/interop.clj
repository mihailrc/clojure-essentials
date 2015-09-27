;reload code in the repl with (use 'clojure-essentials.interop :reload) every time you make changes
(ns clojure-essentials.interop
	(:import [java.util Date]))

; invoke method on object  
(.indexOf "don't worry be happy" "y")
; invoke a static method
(Math/max -2 4)
; both actually share the same syntax
(macroexpand '(.indexOf "don't worry be happy" "y"))
(macroexpand '(Math/max -2 4))
; . special form is the basis for Java interop
;static field
Math/PI
;create new objects - prefered. macroexpand it just for fun
(java.util.HashMap.) 
; or with 
(new java.util.HashMap)
;execute multiple methods on the same object with doto
(doto (java.util.HashMap.)
	(.put "Clojure" "Rich Hickey")
	(.put "LISP" "John McCarthy"))
;macroexpand it to see what happens under the hood

;notice the import at the top. 
(defn greet
	[name]
	(println (str "Hello " name  " today is " (Date.))))
