(ns fullstack-template.core
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [io.pedestal.interceptor :as i]
            [io.pedestal.http.body-params :as body-params])
  (:gen-class))

(defn- root-handler [_]
  {:status 302
   :headers {"Location" "/index.html"}})

(def routes
  (route/expand-routes
   #{["/" :get root-handler :route-name :root]}))

(defn- inject-db [db]
  (i/interceptor {:name ::inject-db
                  :enter (fn [context]
                           (assoc-in context [:request :db] db))}))

(defn pedestal-config [dev? db]
  (-> {::http/routes routes
       ::http/resource-path "public"
       ::http/type :jetty
       ::http/join? false
       ::http/port 3000
       ::http/secure-headers
       {:content-security-policy-settings {:script-src
                                           (if dev?
                                             "'self' 'unsafe-inline' 'unsafe-eval'"
                                             "'self'")}}}
      http/default-interceptors
      (update ::http/interceptors conj (body-params/body-params) (inject-db db))))

(defn create-server [config]
  (-> config
      http/default-interceptors
      http/create-server))

(defonce server (atom nil))
(defn start! [dev?]
  (when (nil? @server)
    (reset! server (-> (pedestal-config dev? (atom {}))
                       create-server
                       http/start))
    :ok))

(defn stop! []
  (when @server
    (http/stop @server)
    (reset! server nil)))

(defn -main [& args]
  (start! false))
