# FullStack Template

## Development
Download Leiningen, and then you can start the code with `lein repl`, as usual.

Then, you can run `watch` on the REPL to start the frontend compilation process.

This code is refreshable with clojure.tools.namespace. Just run `(user/refresh)` and
it'll stop the server, refresh everything, and start the server again.

Then, just point your browser to `localhost:3000` and start developing.

## Installation
Download Leiningen, and run:

```
lein release
```

This will generate the JS file and pack everything in an uberjar for you.

## License

Copyright © 2023 Maurício Szabo

This program and the accompanying materials are made available under the
terms of the MIT License - No Attribution, also known as MIT-0.
