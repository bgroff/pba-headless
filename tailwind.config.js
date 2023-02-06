/** @type {import('tailwindcss').Config} */
const colors = require('tailwindcss/colors')

module.exports = {
  content: ["./src/main/resources/views/**/*.{html,js}"],
  theme: {
    colors: {
      sky: colors.sky,
      teal: colors.teal,
      cyan: colors.cyan,
      rose: colors.rose,
    },
  },
  plugins: [
    require('@tailwindcss/forms'),
    require('@tailwindcss/line-clamp'),
  ],
}
