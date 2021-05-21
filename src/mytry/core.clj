(ns mytry.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

;;function that gives you the first item from the arrow
(defn my-first
      [[first-thing]] ; Notice that first-thing is within a vector
      first-thing)

(my-first ["Gus" "bike" "war-axe"])

(defn too-enthusiastic
      "Return a cheer that might be a bit too enthusiastic"
      [name]
      (str "OH. MY. GOD! " name " YOU ARE MOST DEFINITELY LIKE THE BEST "
               "MAN SLASH WOMAN EVER I LOVE YOU AND WE SHOULD RUN AWAY SOMEWHERE"))

(too-enthusiastic "Gus")

(+ 1 1 1 3)

;;conditionals
(if true :a :b )

;;ranges and maps
(range 1 5)
(map inc (range 1 5))

;;filter
(filter even? (range 10))

;;functions
(defn favorite-things
      [name & things]
      (str "Hi, " name ", here are my favorite things: "
           (clojure.string/join ", " things)))

(favorite-things "Doreen" "gum" "shoes" "kara-te")

;; destructuring
(defn chooser
      [[first-choice second-choice & unimportant-choices]]
      (println (str "Your first choice is: " first-choice))
      (println (str "Your second choice is: " second-choice))
      (println (str "We're ignoring the rest of your choices. "
                    "Here they are in case you need to cry over them: "
                    (clojure.string/join ", " unimportant-choices))))

(chooser ["Marmalade", "Handsome Jack", "Pigpen", "Aquaman"])

;; body functions

(defn number-comment
      [x]
      (if (> x 6)
        "Oh my gosh! What a big number!"
        "That number's OK, I guess"))                       ;;its like an else

(number-comment 5)

;;body functions
;;;maps
(map (fn [name] (str "Hi, " name))
     ["Tiny" "Wini"])

((fn [x] (* x 3)) 8)

;; anonymous functions

(#(* % 3) 8)

(map #(str "hi," %)
     ["Gus" "Mom" "Tiny"])

;; Let

(def x 1)
(let [x 4] x)

(def dalmatian-list
  ["Pongo" "Perdita" "Tiny" "Tomas"])
(let [dalmatians (take 3 dalmatian-list)]
  dalmatians)

;; set (to avoid duplicates)
(into [] (set [:a :a]))

;;loop

(loop [iteration 0]
  (println (str "Iteration " iteration))
  (if (> iteration 3)
    (println "Goodbye!")
    (recur (inc iteration))))

;;Exercises

((fn [x] (+ x 0)) 100)

;;square function

(def square (fn [x] (* x x)))
(square 6)
;; or
(defn square [x] (* x x))
(square 6)

(def v [1 2 3])
(conj v 1)

;;Hello world
(str "Hello" " " "World")
;;or
((fn [] "Hello World"))

