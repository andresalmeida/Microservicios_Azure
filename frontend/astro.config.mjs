// @ts-check
import { defineConfig } from 'astro/config';
import tailwindcss from "@tailwindcss/vite";

// https://astro.build/config
export default defineConfig({
  output: 'static', // Asegura que las rutas sean estáticas en producción
  base: '/', // Asegura que las rutas funcionen correctamente
  vite: {
    plugins: [tailwindcss()],
  },
});
