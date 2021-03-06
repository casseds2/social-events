(ns social-events.routes
  (:require-macros [secretary.core :refer [defroute]])
  (:import goog.History)
  (:require
   [secretary.core :as secretary]
   [goog.events :as gevents]
   [goog.history.EventType :as EventType]
   [re-frame.core :as re-frame]
   [social-events.events :as events]))

(defn hook-browser-navigation! []
  (doto (History.)
    (gevents/listen
     EventType/NAVIGATE
     (fn [event]
       (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

(defn app-routes []
  (secretary/set-config! :prefix "#")
  (defroute "/" []
      (re-frame/dispatch [::events/set-active-panel :home-screen])
      (re-frame/dispatch [::events/fetch-events]))
  (defroute "/about" []
            (re-frame/dispatch [::events/set-active-panel :about-screen]))
  (defroute "/create-event" []
            (re-frame/dispatch [::events/set-active-panel :create-event-screen]))
  (defroute "/event/:id" [id]
      (re-frame/dispatch [::events/set-selected-event id])
      (re-frame/dispatch [::events/set-active-panel :event-screen]))
  (defroute "/event/update/:id" [id]
            (re-frame/dispatch [::events/set-selected-event id])
            (re-frame/dispatch [::events/set-active-panel :update-event-screen]))
  (hook-browser-navigation!))
