var gulp = require('gulp');
var ts = require('gulp-typescript');
var merge = require('merge2');
var sass = require('gulp-sass');
var webserver = require('gulp-webserver');


require("task-gulp-clean")(gulp, {
  clean: './js/'
})

var tsProject = ts.createProject('tsconfig.json');


gulp.task('serve', function() {
  gulp.src('app')
    .pipe(webserver({
      livereload: true,
      directoryListing: false,
      fallback: 'index.html',
      open: true
    }));
});

gulp.task('scripts', function () {
  var tsResult = gulp.src(['./ts/**/*'])
    .pipe(tsProject());;
  return merge([
    tsResult.dts.pipe(gulp.dest('./typings')),
    tsResult.js.pipe(gulp.dest('./js'))
  ]);
});

gulp.task('sass', function () {
  gulp.src('*.scss')
    .pipe(sass.sync().on('error', sass.logError))
    .pipe(gulp.dest('./css'));
});

gulp.task('watch', function () {
  gulp.watch('*.scss', ['sass']);
  gulp.watch('*.ts', ['js']);
});