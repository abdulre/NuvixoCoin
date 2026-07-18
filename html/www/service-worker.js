// NuvixoCoin PWA Service Worker v2.0.0
const CACHE_VERSION = 2;
const CACHE_NAME = 'nuvixocoin-cache-v' + CACHE_VERSION;

const PRECACHE_URLS = [
  'index.html',
  'css/nuvixocoin.css',
  'img/nuvixo-icon-192.svg',
  'img/nuvixo-icon-512.svg',
  'img/nuvixo-logo-full.svg',
  'img/nuvixo-logo-circle.svg',
  'manifest.json'
];

self.addEventListener('install', event => {
  event.waitUntil(
    caches.open(CACHE_NAME)
      .then(cache => cache.addAll(PRECACHE_URLS))
      .then(() => self.skipWaiting())
  );
});

self.addEventListener('activate', event => {
  event.waitUntil(
    caches.keys().then(cacheNames =>
      Promise.all(
        cacheNames
          .filter(name => name !== CACHE_NAME)
          .map(name => caches.delete(name))
      )
    ).then(() => self.clients.claim())
  );
});

self.addEventListener('fetch', event => {
  // Don't cache API calls
  if (event.request.url.includes('/nxt?') ||
      event.request.url.includes('requestType=')) {
    return;
  }
  event.respondWith(
    caches.match(event.request)
      .then(cached => cached || fetch(event.request)
        .then(response => {
          if (response.ok) {
            const clone = response.clone();
            caches.open(CACHE_NAME).then(cache => cache.put(event.request, clone));
          }
          return response;
        })
      )
  );
});
