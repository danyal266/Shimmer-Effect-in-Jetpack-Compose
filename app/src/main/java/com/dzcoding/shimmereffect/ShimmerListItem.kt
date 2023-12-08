package com.dzcoding.shimmereffect

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerListItem(
    isLoading:Boolean,
    isContentAfterLoading:@Composable ()->Unit,
    modifier: Modifier = Modifier
){
    if (isLoading){
        Row (modifier = modifier){
            Box(modifier = Modifier.size(100.dp).shimmerEffect().padding(16.dp).clip(CircleShape))
            Spacer(modifier = Modifier.shimmerEffect().width(16.dp))
            Column (modifier = Modifier.weight(1f)){
                Box(modifier = Modifier.fillMaxWidth().shimmerEffect().height(16.dp).padding(10.dp))
                Spacer(modifier = Modifier.height(16.dp).shimmerEffect())
                Box(modifier = Modifier.fillMaxWidth(0.7f).shimmerEffect().height(16.dp).padding(10.dp))
            }
        }
    }else{
        isContentAfterLoading()
    }
}
fun Modifier.shimmerEffect():Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition(label = "")
    val startOffsetX by transition.animateFloat(
        initialValue = -2*size.width.toFloat(),
        targetValue = 2*size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000
            )
        ), label = ""
    )
    background(
        brush = Brush.linearGradient(
            listOf(
                Color(0xFFBEB7B7),
                Color(0xFF968E8E),
                Color(0xFFC7C0C0),
            ),
            start = Offset(startOffsetX,0f),
            end = Offset(startOffsetX*size.width.toFloat(),startOffsetX*size.height.toFloat())
        )
    ).onGloballyPositioned {
        size = it.size
    }
}
