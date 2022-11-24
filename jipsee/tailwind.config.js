/** @type {import('tailwindcss').Config} */
// npx tailwindcss -i ./src/input.css -o ./dist/output.css --watch

//const px0_10 = { ...Array.from(Array(11)).map((_, i) => `${i}px`) };
const px0_100 = { ...Array.from(Array(101)).map((_, i) => `${i}px`) };
const px0_200 = { ...Array.from(Array(201)).map((_, i) => `${i}px`) };
//const px0_1000 = { ...Array.from(Array(1001)).map((_, i) => `${i}px`) };
const px0_2000 = { ...Array.from(Array(2001)).map((_, i) => `${i}px`) };

module.exports = {
  content: ["./src/**/*.{html,js,vue}"],
  theme: {
    extend: {
      borderWidth: px0_100,
      borderRadius: px0_100,
      fontSize: px0_100,
      lineHeight: px0_100,
      width: px0_2000,
      height: px0_2000,
      margin: px0_200,
      padding: px0_200,
      minWidth: px0_2000,
      minHeight: px0_2000,
      spacing: px0_200,
      colors: {
        "yellow-400": "#fad203",
        "yellow-700": "#432817",
        black: "#2a2a2a",
      },
    },
  },
  plugins: [
    require("@tailwindcss/forms"),
    require("tailwind-scrollbar")({
      nocompatible: true,
    }),
  ],
};
