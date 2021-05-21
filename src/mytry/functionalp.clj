(ns mytry.functionalp)
;;examples
(defn wisdom
  [words]
  (str words ", Gustavo-san"))
(wisdom "Always bathe on Fridays")
;;
(def great-baby-name "Rosanthony")
great-baby-name
(let [great-baby-name "Bloodthunder"]
  great-baby-name)
great-baby-name

;;general approach to recursive problem solving
(defn sum
     ([vals] (sum vals 0))
  ([vals accumulating-total]
         (if (empty? vals)
            accumulating-total
            (sum (rest vals) (+ (first vals) accumulating-total)))))

;;using recur
(defn sum
  ([vals] (sum vals 0))
  ([vals accumulating-total]
   (if (empty? vals)
     accumulating-total
     (recur (rest vals) (+ (first vals) accumulating-total)))))

;;function composition example
(require '[clojure.string :as s])                           ;;access to string function library
(defn clean                                                 ;;clean function works by passing an immutable value
  [text]                                                    ;;in this case text
  (s/replace (s/trim text) #"lol" "LOL"))                   ;;to a pure function s/trim
(clean "My boa constrictor is so sassy lol!  ")             ;;immutable value then its passed to a pure funct s/replace and returns with LOL

;; comp examples
((comp inc *) 2 3)
;;comp to describe attributes
(def character
  {:name "Smooches McCutes"
   :attributes {:intelligence 10
                :strength 4
                :dexterity 5}})
(def c-int (comp :intelligence :attributes))
(def c-str (comp :strength :attributes))
(def c-dex (comp :dexterity :attributes))

(c-int character)                                           ;;=>10
(c-str character)                                           ;:=>4
(c-dex character)                                           ;;=>5
;;
(defn two-comp
  [f g]
  (fn [& args]
    (f (apply g args))))

;;memoize examples
(defn sleepy-identity
  "Returns the given value after 1 second"
  [x]
  (Thread/sleep 1000)
  x)
(sleepy-identity "Mr. Fantastico")
;;now the same, but with memoize
(def memo-sleepy-identity (memoize sleepy-identity))
(memo-sleepy-identity "Mr. Fantastico")                     ;;now after using it once, its immediate

