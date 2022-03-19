# Thermomether app - UI

To execute this frontend, just ensure the backend is running on port `8080` and because of CORS policy in Chrome's browser. Although the frontend is receiving that, in fact,
it is an allowed origin, the chrome's policy is blocking the request anyway.

To avoid this behavior you can execute Chrome with `--disable-web-security` flag. Use this with extra care (do not navigate away using flag)

- Linux:
```bash
$ google-chrome --disable-web-security
```
- Windows
Hit `Ctrl + X` and the type in the `Run...` app that will appear:

```prompt
chrome.exe --user-data-dir="C://Chrome dev session" --disable-web-security
```

