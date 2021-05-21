(ns mytry.examples)

;;Chapt 4

(defn titleize
  [topic]
  (str topic " for the Brave and True"))

(map titleize ["Hamsters" "Ragnarok"])
; => ("Hamsters for the Brave and True" "Ragnarok for the Brave and True")

(map titleize '("Empathy" "Decorating"))
; => ("Empathy for the Brave and True" "Decorating for the Brave and True")

(map titleize #{"Elbows" "Soap Carving"})
; => ("Elbows for the Brave and True" "Soap Carving for the Brave and True")

(map #(titleize (second %)) {:uncomfortable-thing "Winking"})
; => ("Winking for the Brave and True")

;;linked lists
;;;;first: returns the value for the requested node
;;;;rest: returns the remaining values after the requested node
;;;;cons: adds a new node with the given value to the beginning of the list
;;seqs
(seq '(1 2 3))

(seq [1 2 3])

(seq #{1 2 3})

;;maps
(map str ["a" "b" "c"] ["A" "B" "C"])

(def human-consumption   [8.1 7.3 6.6 5.0])
(def critter-consumption [0.0 0.2 0.3 1.1])
(defn unify-diet-data
  [human critter]
  {:human human
   :critter critter})

(map unify-diet-data human-consumption critter-consumption)

;; vampire example
(def human-consumption   [8.1 7.3 6.6 5.0])
(def critter-consumption [0.0 0.2 0.3 1.1])
(defn unify-diet-data
  [human critter]
  {:human human
   :critter critter})

(map unify-diet-data human-consumption critter-consumption)

;; sum count avg - function stats iterates over a vector of functions
(def sum #(reduce + %))
(def avg #(/ (sum %) (count %)))
(defn stats
  [numbers]
  (map #(% numbers) [sum count avg]))

(stats [3 4 10])
(stats [80 1 44 13 6])

;;map example
(def identities
  [{:alias "Batman" :real "Bruce Wayne"}
   {:alias "Spider-Man" :real "Peter Parker"}
   {:alias "Santa" :real "Your mom"}
   {:alias "Easter Bunny" :real "Your dad"}])

(map :real identities)

;;reduce usage 1 - transform values of a map
(reduce (fn [new-map [key val]]
          (assoc new-map key (inc val)))
        {}
        {:max 30 :min 10})

;;reduce usage 2 - filter keys from a map based on their value
(reduce (fn [new-map [key val]]
          (if (> val 4)
            (assoc new-map key val)
            new-map))
        {}
        {:human 4.1
         :critter 3.9})

;;take & drop
(take 3 [1 2 3 4 5 6 7 8 9 10])

(drop 3 [1 2 3 4 5 6 7 8 9 10])

;;food journal take-while & drop-while
(def food-journal
  [{:month 1 :day 1 :human 5.3 :critter 2.3}
   {:month 1 :day 2 :human 5.1 :critter 2.0}
   {:month 2 :day 1 :human 4.9 :critter 2.1}
   {:month 2 :day 2 :human 5.0 :critter 2.5}
   {:month 3 :day 1 :human 4.2 :critter 3.3}
   {:month 3 :day 2 :human 4.0 :critter 3.8}
   {:month 4 :day 1 :human 3.7 :critter 3.9}
   {:month 4 :day 2 :human 3.7 :critter 3.6}])

(take-while #(< (:month %) 3) food-journal)
;;drop-while
(drop-while #(< (:month %) 3) food-journal)
;; feb and march data using take-while & drop-while
(take-while #(< (:month %) 4)
            (drop-while #(< (:month %) 2) food-journal))

;;filter journal entries where human consumption is less than 5
(filter #(< (:human %) 5) food-journal)

(filter #(< (:month %) 3) food-journal)                     ;; same to drop-while

;;some - usage
(some #(> (:month %) 3) food-journal)                       ;;nil

(some #(> (:month %) 5) food-journal)                       ;;true

(some #(and (> (:critter %) 3) %) food-journal)             ;;to get the entry

;;sort: organize elements in ascending order
(sort [3 1 2])

;;sort-by: a more complex classification
(sort-by count ["aaa" "c" "bb"])

;;concat: adds the member of one seq to the end of another
(concat [1 2] [3 4])

;;Lazy seqs: identify a vampire
(def vampire-database
  {0 {:makes-blood-puns? false, :has-pulse? true  :name "McFishwich"}
   1 {:makes-blood-puns? false, :has-pulse? true  :name "McMackson"}
   2 {:makes-blood-puns? true,  :has-pulse? false :name "Damon Salvatore"}
   3 {:makes-blood-puns? true,  :has-pulse? true  :name "Mickey Mouse"}})

(defn vampire-related-details
  [social-security-number]
  (Thread/sleep 1000)
  (get vampire-database social-security-number))

(defn vampire?
  [record]
  (and (:makes-blood-puns? record)
       (not (:has-pulse? record))
       record))

(defn identify-vampire
  [social-security-numbers]
  (first (filter vampire?
                 (map vampire-related-details social-security-numbers))))

;;time example: prints time taken by the given operation
(time (vampire-related-details 0))                          ;;use this

(time (first mapped-details))                               ;;takes 32 seconds

(time (identify-vampire (range 0 1000000)))                 ;;identify vampire

;;infinite seqs
;; repeat
(concat (take 8 (repeat "na")) ["Batman!"])
;;repeatedly
(take 4 (repeatedly (fn [] (rand-int 10))))
;;even-numbers-numpares
(defn even-numbers
  ([] (even-numbers 0))
  ([n] (cons n (lazy-seq (even-numbers (+ n 2))))))

(take 10 (even-numbers))

;;into
(map identity {:sunlight-reaction "Glitter!"})
(into {} (map identity {:sunlight-reaction "Glitter!"}))    ;;returns a sq data structure after giving a map dat struct, into converts the seq back into map
(map identity [:garlic :sesame-oil :fried-eggs])            ;;returns a seq
(into [] (map identity [:garlic :sesame-oil :fried-eggs]))  ;;returns a vector(data structure)

;;set example
(map identity [:garlic-clove :garlic-clove])
;;with set
(into #{} (map identity [:garlic-clove :garlic-clove]))

;;into with something inside
(into {:favorite-emotion "gloomy"} [[:sunlight-reaction "Glitter!"]])
(into ["cherry"] '("pine" "spruce"))
(into {:favorite-animal "kitty"} {:least-favorite-smell "dog"
                                  :relationship-with-teenager "creepy"})

;;conj
(conj {:time "midnight"} [:place "ye olde cemetarium"])

;;apply
(max 0 1 2)
(max [0 1 2])                                               ;;wrong compares the vector
(apply max [0 1 2])                                         ;;right one

;;partial
(def add10 (partial + 10))
(add10 3)
(add10 5)

(def add-missing-elements
  (partial conj ["water" "earth" "air"]))
(add-missing-elements "unobtainium" "adamantium")

(defn my-partial
  [partialized-fn & args]
  (fn [& more-args]
    (apply partialized-fn (into args more-args))))
(def add20 (my-partial + 20))                               ;;anonymous func
(add20 3)

(defn lousy-logger
  [log-level message]
  (condp = log-level
    :warn (clojure.string/lower-case message)
    :emergency (clojure.string/upper-case message)))
(def warn (partial lousy-logger :warn))
(def emergency (partial lousy-logger :emergency))
(warn "Red light ahead")
(emergency "Run in circles")

;;complement
(defn my-complement
  [fun]
  (fn [& args]
    (not (apply fun args))))

(def my-pos? (complement neg?))
(my-pos? 1)

