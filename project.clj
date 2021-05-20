(defproject fullstack-template "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [http-kit "2.5.3"]
                 [io.pedestal/pedestal.service "0.5.8"]
                 [io.pedestal/pedestal.jetty "0.5.8"]]

  :main ^:skip-aot fullstack-template.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}
             :dev {:source-paths ["dev"]
                   :dependencies [[nubank/matcher-combinators "3.1.4"
                                   :exclusions [midje]]
                                  [thheller/shadow-cljs "2.13.0"]
                                  [reagent "1.0.0"]
                                  [org.clojure/tools.namespace "1.0.0"]]}}

  :aliases {"release-frontend" ["run" "-m" "user/build-release"]
            "release" ["do" "release-frontend," "uberjar"]}

  :repl-options {:init-ns user})
