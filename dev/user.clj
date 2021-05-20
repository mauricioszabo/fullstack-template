(ns user
  (:require [clojure.tools.namespace.repl :as repl]
            [shadow.cljs.devtools.server :as server]
            [shadow.cljs.devtools.api :as shadow]))

(defn- start! []
  (let [start (requiring-resolve 'fullstack-template.core/start!)]
    (start true)))

(defn refresh []
  (when-let [stop (requiring-resolve 'fullstack-template.core/stop!)]
    (stop))
  (repl/refresh :after 'user/start!))

(defn watch []
  (server/start!)
  (shadow/watch :app))

(defn unwatch []
  (shadow/stop-worker :app)
  (server/stop!))

(defn build-release []
  (shadow/release :app))
