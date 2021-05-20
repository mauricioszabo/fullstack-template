(ns fullstack-template.frontend
  (:require [reagent.core :as r]
            [reagent.dom :as r-dom]))

(defonce ^:private state
  (r/atom {:clicked 0}))

(defn- increment-counter! [_ to-increment]
  (swap! state update :clicked + to-increment))

(defn- handle-event [function & params]
  (fn [^js evt]
    (.preventDefault evt)
    (.stopPropagation evt)
    (apply function evt params)))

(defn- index []
  [:<>
   [:h1 "Hello, World!"]
   [:button
    {:on-click (handle-event increment-counter! 1)}
    (str "This button as clicked " (:clicked @state) " times")]])

(defn ^:dev/after-load main []
  (r-dom/render [index] (js/document.querySelector "div#app")))
