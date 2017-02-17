<@override name="body">
<div id="carousel-example-generic" class="carousel slide" data-ride="carousel" style="margin-top: -10px;">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
        <div class="item active">
            <img src="" alt="">
            <div class="carousel-caption">
            </div>
        </div>
        <div class="item">
            <img src="" alt="">
            <div class="carousel-caption">
            </div>
        </div>
        <div class="item">
            <img src="" alt="">
            <div class="carousel-caption">
            </div>
        </div>
    </div>
</div>

<script type="application/javascript">
    $('.carousel').carousel({
        interval: 5000
    })
</script>

</@override>
<@extends name="/common/base.ftl"/>