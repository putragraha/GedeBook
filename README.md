# GedeBook

Android TV Application which fetching books from Gutenberg Library (API source: http://gutendex.com/).

## Tech Stack
### Production Environment
- Leanback, your favourite library for building UI in Android TV App
- Retrofit, for REST API
- Moshi, for Object mapper from JSON
- Glide, load image into ImageView
- Hilt, for Dependency Injection

### Test Environment
- Mockk
- Kotlin Coroutine Test, enable coroutine environment in test
- Flow Turbine, for verify flow value

## Development Details
- Built by using Clean Architecture approach, separate data, domain, and presentation layer
- Using TDD approach by implementing Red, Green, Refactor style
