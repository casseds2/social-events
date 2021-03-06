(ns social-events.subs
  (:require
   [re-frame.core :refer [reg-sub]]))

(reg-sub
 ::active-panel
 (fn [db _]
   (:active-panel db)))

(reg-sub
  ::events
  (fn [db _]
    (:events db)))

(reg-sub
  ::selected-event
  (fn [db _]
    (:selected-event db)))

(reg-sub
  ::creating-event?
  (fn [db _]
    (get-in db [:app-state :creating-event?])))

(reg-sub
  ::fetching-events?
  (fn [db _]
    (get-in db [:app-state :fetching-events?])))

(reg-sub
  ::fetching-event?
  (fn [db _]
    (get-in db [:app-state :fetching-event?])))