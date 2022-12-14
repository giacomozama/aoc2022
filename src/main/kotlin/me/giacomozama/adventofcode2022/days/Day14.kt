package me.giacomozama.adventofcode2022.days

import kotlin.math.sign

class Day14 : Day() {

    private val input: Array<Array<IntArray>> = arrayOf(
        arrayOf(intArrayOf(502, 19), intArrayOf(507, 19)),
        arrayOf(
            intArrayOf(523, 100), intArrayOf(523, 104), intArrayOf(519, 104), intArrayOf(519, 111),
            intArrayOf(528, 111), intArrayOf(528, 104), intArrayOf(526, 104), intArrayOf(526, 100)
        ),
        arrayOf(
            intArrayOf(488, 59), intArrayOf(488, 51), intArrayOf(488, 59), intArrayOf(490, 59), intArrayOf(490, 52),
            intArrayOf(490, 59), intArrayOf(492, 59), intArrayOf(492, 55), intArrayOf(492, 59), intArrayOf(494, 59),
            intArrayOf(494, 52), intArrayOf(494, 59), intArrayOf(496, 59), intArrayOf(496, 50), intArrayOf(496, 59),
            intArrayOf(498, 59), intArrayOf(498, 58), intArrayOf(498, 59), intArrayOf(500, 59), intArrayOf(500, 56),
            intArrayOf(500, 59), intArrayOf(502, 59), intArrayOf(502, 56), intArrayOf(502, 59), intArrayOf(504, 59),
            intArrayOf(504, 57), intArrayOf(504, 59)
        ),
        arrayOf(intArrayOf(507, 117), intArrayOf(521, 117), intArrayOf(521, 116)),
        arrayOf(intArrayOf(517, 34), intArrayOf(522, 34)),
        arrayOf(
            intArrayOf(513, 97), intArrayOf(513, 89), intArrayOf(513, 97), intArrayOf(515, 97), intArrayOf(515, 94),
            intArrayOf(515, 97), intArrayOf(517, 97), intArrayOf(517, 94), intArrayOf(517, 97), intArrayOf(519, 97),
            intArrayOf(519, 93), intArrayOf(519, 97), intArrayOf(521, 97), intArrayOf(521, 88), intArrayOf(521, 97),
            intArrayOf(523, 97), intArrayOf(523, 94), intArrayOf(523, 97)
        ),
        arrayOf(
            intArrayOf(513, 97), intArrayOf(513, 89), intArrayOf(513, 97), intArrayOf(515, 97), intArrayOf(515, 94),
            intArrayOf(515, 97), intArrayOf(517, 97), intArrayOf(517, 94), intArrayOf(517, 97), intArrayOf(519, 97),
            intArrayOf(519, 93), intArrayOf(519, 97), intArrayOf(521, 97), intArrayOf(521, 88), intArrayOf(521, 97),
            intArrayOf(523, 97), intArrayOf(523, 94), intArrayOf(523, 97)
        ),
        arrayOf(
            intArrayOf(510, 75), intArrayOf(510, 78), intArrayOf(505, 78), intArrayOf(505, 84), intArrayOf(518, 84),
            intArrayOf(518, 78), intArrayOf(515, 78), intArrayOf(515, 75)
        ),
        arrayOf(intArrayOf(503, 34), intArrayOf(508, 34)),
        arrayOf(intArrayOf(501, 15), intArrayOf(506, 15)),
        arrayOf(
            intArrayOf(523, 136), intArrayOf(523, 138), intArrayOf(518, 138), intArrayOf(518, 145),
            intArrayOf(535, 145), intArrayOf(535, 138), intArrayOf(528, 138), intArrayOf(528, 136)
        ),
        arrayOf(intArrayOf(513, 113), intArrayOf(513, 114), intArrayOf(527, 114)),
        arrayOf(
            intArrayOf(488, 59), intArrayOf(488, 51), intArrayOf(488, 59), intArrayOf(490, 59), intArrayOf(490, 52),
            intArrayOf(490, 59), intArrayOf(492, 59), intArrayOf(492, 55), intArrayOf(492, 59), intArrayOf(494, 59),
            intArrayOf(494, 52), intArrayOf(494, 59), intArrayOf(496, 59), intArrayOf(496, 50), intArrayOf(496, 59),
            intArrayOf(498, 59), intArrayOf(498, 58), intArrayOf(498, 59), intArrayOf(500, 59), intArrayOf(500, 56),
            intArrayOf(500, 59), intArrayOf(502, 59), intArrayOf(502, 56), intArrayOf(502, 59), intArrayOf(504, 59),
            intArrayOf(504, 57), intArrayOf(504, 59)
        ),
        arrayOf(
            intArrayOf(497, 72), intArrayOf(497, 63), intArrayOf(497, 72), intArrayOf(499, 72), intArrayOf(499, 65),
            intArrayOf(499, 72), intArrayOf(501, 72), intArrayOf(501, 71), intArrayOf(501, 72), intArrayOf(503, 72),
            intArrayOf(503, 67), intArrayOf(503, 72), intArrayOf(505, 72), intArrayOf(505, 63), intArrayOf(505, 72),
            intArrayOf(507, 72), intArrayOf(507, 71), intArrayOf(507, 72), intArrayOf(509, 72), intArrayOf(509, 70),
            intArrayOf(509, 72), intArrayOf(511, 72), intArrayOf(511, 69), intArrayOf(511, 72)
        ),
        arrayOf(
            intArrayOf(497, 72), intArrayOf(497, 63), intArrayOf(497, 72), intArrayOf(499, 72), intArrayOf(499, 65),
            intArrayOf(499, 72), intArrayOf(501, 72), intArrayOf(501, 71), intArrayOf(501, 72), intArrayOf(503, 72),
            intArrayOf(503, 67), intArrayOf(503, 72), intArrayOf(505, 72), intArrayOf(505, 63), intArrayOf(505, 72),
            intArrayOf(507, 72), intArrayOf(507, 71), intArrayOf(507, 72), intArrayOf(509, 72), intArrayOf(509, 70),
            intArrayOf(509, 72), intArrayOf(511, 72), intArrayOf(511, 69), intArrayOf(511, 72)
        ),
        arrayOf(
            intArrayOf(497, 72), intArrayOf(497, 63), intArrayOf(497, 72), intArrayOf(499, 72), intArrayOf(499, 65),
            intArrayOf(499, 72), intArrayOf(501, 72), intArrayOf(501, 71), intArrayOf(501, 72), intArrayOf(503, 72),
            intArrayOf(503, 67), intArrayOf(503, 72), intArrayOf(505, 72), intArrayOf(505, 63), intArrayOf(505, 72),
            intArrayOf(507, 72), intArrayOf(507, 71), intArrayOf(507, 72), intArrayOf(509, 72), intArrayOf(509, 70),
            intArrayOf(509, 72), intArrayOf(511, 72), intArrayOf(511, 69), intArrayOf(511, 72)
        ),
        arrayOf(
            intArrayOf(497, 72), intArrayOf(497, 63), intArrayOf(497, 72), intArrayOf(499, 72), intArrayOf(499, 65),
            intArrayOf(499, 72), intArrayOf(501, 72), intArrayOf(501, 71), intArrayOf(501, 72), intArrayOf(503, 72),
            intArrayOf(503, 67), intArrayOf(503, 72), intArrayOf(505, 72), intArrayOf(505, 63), intArrayOf(505, 72),
            intArrayOf(507, 72), intArrayOf(507, 71), intArrayOf(507, 72), intArrayOf(509, 72), intArrayOf(509, 70),
            intArrayOf(509, 72), intArrayOf(511, 72), intArrayOf(511, 69), intArrayOf(511, 72)
        ),
        arrayOf(
            intArrayOf(488, 59), intArrayOf(488, 51), intArrayOf(488, 59), intArrayOf(490, 59), intArrayOf(490, 52),
            intArrayOf(490, 59), intArrayOf(492, 59), intArrayOf(492, 55), intArrayOf(492, 59), intArrayOf(494, 59),
            intArrayOf(494, 52), intArrayOf(494, 59), intArrayOf(496, 59), intArrayOf(496, 50), intArrayOf(496, 59),
            intArrayOf(498, 59), intArrayOf(498, 58), intArrayOf(498, 59), intArrayOf(500, 59), intArrayOf(500, 56),
            intArrayOf(500, 59), intArrayOf(502, 59), intArrayOf(502, 56), intArrayOf(502, 59), intArrayOf(504, 59),
            intArrayOf(504, 57), intArrayOf(504, 59)
        ),
        arrayOf(
            intArrayOf(523, 136), intArrayOf(523, 138), intArrayOf(518, 138), intArrayOf(518, 145),
            intArrayOf(535, 145), intArrayOf(535, 138), intArrayOf(528, 138), intArrayOf(528, 136)
        ),
        arrayOf(intArrayOf(530, 150), intArrayOf(535, 150)),
        arrayOf(
            intArrayOf(526, 133), intArrayOf(526, 131), intArrayOf(526, 133), intArrayOf(528, 133),
            intArrayOf(528, 129), intArrayOf(528, 133), intArrayOf(530, 133), intArrayOf(530, 129), intArrayOf(530, 133)
        ),
        arrayOf(
            intArrayOf(523, 100), intArrayOf(523, 104), intArrayOf(519, 104), intArrayOf(519, 111),
            intArrayOf(528, 111), intArrayOf(528, 104), intArrayOf(526, 104), intArrayOf(526, 100)
        ),
        arrayOf(
            intArrayOf(488, 59), intArrayOf(488, 51), intArrayOf(488, 59), intArrayOf(490, 59), intArrayOf(490, 52),
            intArrayOf(490, 59), intArrayOf(492, 59), intArrayOf(492, 55), intArrayOf(492, 59), intArrayOf(494, 59),
            intArrayOf(494, 52), intArrayOf(494, 59), intArrayOf(496, 59), intArrayOf(496, 50), intArrayOf(496, 59),
            intArrayOf(498, 59), intArrayOf(498, 58), intArrayOf(498, 59), intArrayOf(500, 59), intArrayOf(500, 56),
            intArrayOf(500, 59), intArrayOf(502, 59), intArrayOf(502, 56), intArrayOf(502, 59), intArrayOf(504, 59),
            intArrayOf(504, 57), intArrayOf(504, 59)
        ),
        arrayOf(intArrayOf(510, 34), intArrayOf(515, 34)),
        arrayOf(
            intArrayOf(497, 72), intArrayOf(497, 63), intArrayOf(497, 72), intArrayOf(499, 72), intArrayOf(499, 65),
            intArrayOf(499, 72), intArrayOf(501, 72), intArrayOf(501, 71), intArrayOf(501, 72), intArrayOf(503, 72),
            intArrayOf(503, 67), intArrayOf(503, 72), intArrayOf(505, 72), intArrayOf(505, 63), intArrayOf(505, 72),
            intArrayOf(507, 72), intArrayOf(507, 71), intArrayOf(507, 72), intArrayOf(509, 72), intArrayOf(509, 70),
            intArrayOf(509, 72), intArrayOf(511, 72), intArrayOf(511, 69), intArrayOf(511, 72)
        ),
        arrayOf(
            intArrayOf(510, 75), intArrayOf(510, 78), intArrayOf(505, 78), intArrayOf(505, 84), intArrayOf(518, 84),
            intArrayOf(518, 78), intArrayOf(515, 78), intArrayOf(515, 75)
        ),
        arrayOf(
            intArrayOf(488, 59), intArrayOf(488, 51), intArrayOf(488, 59), intArrayOf(490, 59), intArrayOf(490, 52),
            intArrayOf(490, 59), intArrayOf(492, 59), intArrayOf(492, 55), intArrayOf(492, 59), intArrayOf(494, 59),
            intArrayOf(494, 52), intArrayOf(494, 59), intArrayOf(496, 59), intArrayOf(496, 50), intArrayOf(496, 59),
            intArrayOf(498, 59), intArrayOf(498, 58), intArrayOf(498, 59), intArrayOf(500, 59), intArrayOf(500, 56),
            intArrayOf(500, 59), intArrayOf(502, 59), intArrayOf(502, 56), intArrayOf(502, 59), intArrayOf(504, 59),
            intArrayOf(504, 57), intArrayOf(504, 59)
        ),
        arrayOf(
            intArrayOf(488, 59), intArrayOf(488, 51), intArrayOf(488, 59), intArrayOf(490, 59), intArrayOf(490, 52),
            intArrayOf(490, 59), intArrayOf(492, 59), intArrayOf(492, 55), intArrayOf(492, 59), intArrayOf(494, 59),
            intArrayOf(494, 52), intArrayOf(494, 59), intArrayOf(496, 59), intArrayOf(496, 50), intArrayOf(496, 59),
            intArrayOf(498, 59), intArrayOf(498, 58), intArrayOf(498, 59), intArrayOf(500, 59), intArrayOf(500, 56),
            intArrayOf(500, 59), intArrayOf(502, 59), intArrayOf(502, 56), intArrayOf(502, 59), intArrayOf(504, 59),
            intArrayOf(504, 57), intArrayOf(504, 59)
        ),
        arrayOf(intArrayOf(524, 34), intArrayOf(529, 34)),
        arrayOf(intArrayOf(498, 46), intArrayOf(502, 46)),
        arrayOf(intArrayOf(510, 46), intArrayOf(514, 46)),
        arrayOf(intArrayOf(509, 19), intArrayOf(514, 19)),
        arrayOf(
            intArrayOf(513, 97), intArrayOf(513, 89), intArrayOf(513, 97), intArrayOf(515, 97), intArrayOf(515, 94),
            intArrayOf(515, 97), intArrayOf(517, 97), intArrayOf(517, 94), intArrayOf(517, 97), intArrayOf(519, 97),
            intArrayOf(519, 93), intArrayOf(519, 97), intArrayOf(521, 97), intArrayOf(521, 88), intArrayOf(521, 97),
            intArrayOf(523, 97), intArrayOf(523, 94), intArrayOf(523, 97)
        ),
        arrayOf(
            intArrayOf(526, 133), intArrayOf(526, 131), intArrayOf(526, 133), intArrayOf(528, 133),
            intArrayOf(528, 129), intArrayOf(528, 133), intArrayOf(530, 133), intArrayOf(530, 129), intArrayOf(530, 133)
        ),
        arrayOf(intArrayOf(497, 13), intArrayOf(502, 13)),
        arrayOf(intArrayOf(504, 46), intArrayOf(508, 46)),
        arrayOf(
            intArrayOf(488, 59), intArrayOf(488, 51), intArrayOf(488, 59), intArrayOf(490, 59), intArrayOf(490, 52),
            intArrayOf(490, 59), intArrayOf(492, 59), intArrayOf(492, 55), intArrayOf(492, 59), intArrayOf(494, 59),
            intArrayOf(494, 52), intArrayOf(494, 59), intArrayOf(496, 59), intArrayOf(496, 50), intArrayOf(496, 59),
            intArrayOf(498, 59), intArrayOf(498, 58), intArrayOf(498, 59), intArrayOf(500, 59), intArrayOf(500, 56),
            intArrayOf(500, 59), intArrayOf(502, 59), intArrayOf(502, 56), intArrayOf(502, 59), intArrayOf(504, 59),
            intArrayOf(504, 57), intArrayOf(504, 59)
        ),
        arrayOf(
            intArrayOf(545, 155), intArrayOf(545, 157), intArrayOf(541, 157), intArrayOf(541, 160),
            intArrayOf(556, 160), intArrayOf(556, 157), intArrayOf(550, 157), intArrayOf(550, 155)
        ),
        arrayOf(
            intArrayOf(513, 97), intArrayOf(513, 89), intArrayOf(513, 97), intArrayOf(515, 97), intArrayOf(515, 94),
            intArrayOf(515, 97), intArrayOf(517, 97), intArrayOf(517, 94), intArrayOf(517, 97), intArrayOf(519, 97),
            intArrayOf(519, 93), intArrayOf(519, 97), intArrayOf(521, 97), intArrayOf(521, 88), intArrayOf(521, 97),
            intArrayOf(523, 97), intArrayOf(523, 94), intArrayOf(523, 97)
        ),
        arrayOf(
            intArrayOf(510, 75), intArrayOf(510, 78), intArrayOf(505, 78), intArrayOf(505, 84), intArrayOf(518, 84),
            intArrayOf(518, 78), intArrayOf(515, 78), intArrayOf(515, 75)
        ),
        arrayOf(
            intArrayOf(497, 72), intArrayOf(497, 63), intArrayOf(497, 72), intArrayOf(499, 72), intArrayOf(499, 65),
            intArrayOf(499, 72), intArrayOf(501, 72), intArrayOf(501, 71), intArrayOf(501, 72), intArrayOf(503, 72),
            intArrayOf(503, 67), intArrayOf(503, 72), intArrayOf(505, 72), intArrayOf(505, 63), intArrayOf(505, 72),
            intArrayOf(507, 72), intArrayOf(507, 71), intArrayOf(507, 72), intArrayOf(509, 72), intArrayOf(509, 70),
            intArrayOf(509, 72), intArrayOf(511, 72), intArrayOf(511, 69), intArrayOf(511, 72)
        ),
        arrayOf(
            intArrayOf(513, 97), intArrayOf(513, 89), intArrayOf(513, 97), intArrayOf(515, 97), intArrayOf(515, 94),
            intArrayOf(515, 97), intArrayOf(517, 97), intArrayOf(517, 94), intArrayOf(517, 97), intArrayOf(519, 97),
            intArrayOf(519, 93), intArrayOf(519, 97), intArrayOf(521, 97), intArrayOf(521, 88), intArrayOf(521, 97),
            intArrayOf(523, 97), intArrayOf(523, 94), intArrayOf(523, 97)
        ),
        arrayOf(
            intArrayOf(488, 59), intArrayOf(488, 51), intArrayOf(488, 59), intArrayOf(490, 59), intArrayOf(490, 52),
            intArrayOf(490, 59), intArrayOf(492, 59), intArrayOf(492, 55), intArrayOf(492, 59), intArrayOf(494, 59),
            intArrayOf(494, 52), intArrayOf(494, 59), intArrayOf(496, 59), intArrayOf(496, 50), intArrayOf(496, 59),
            intArrayOf(498, 59), intArrayOf(498, 58), intArrayOf(498, 59), intArrayOf(500, 59), intArrayOf(500, 56),
            intArrayOf(500, 59), intArrayOf(502, 59), intArrayOf(502, 56), intArrayOf(502, 59), intArrayOf(504, 59),
            intArrayOf(504, 57), intArrayOf(504, 59)
        ),
        arrayOf(
            intArrayOf(488, 59), intArrayOf(488, 51), intArrayOf(488, 59), intArrayOf(490, 59), intArrayOf(490, 52),
            intArrayOf(490, 59), intArrayOf(492, 59), intArrayOf(492, 55), intArrayOf(492, 59), intArrayOf(494, 59),
            intArrayOf(494, 52), intArrayOf(494, 59), intArrayOf(496, 59), intArrayOf(496, 50), intArrayOf(496, 59),
            intArrayOf(498, 59), intArrayOf(498, 58), intArrayOf(498, 59), intArrayOf(500, 59), intArrayOf(500, 56),
            intArrayOf(500, 59), intArrayOf(502, 59), intArrayOf(502, 56), intArrayOf(502, 59), intArrayOf(504, 59),
            intArrayOf(504, 57), intArrayOf(504, 59)
        ),
        arrayOf(intArrayOf(507, 43), intArrayOf(511, 43)),
        arrayOf(
            intArrayOf(523, 100), intArrayOf(523, 104), intArrayOf(519, 104), intArrayOf(519, 111),
            intArrayOf(528, 111), intArrayOf(528, 104), intArrayOf(526, 104), intArrayOf(526, 100)
        ),
        arrayOf(intArrayOf(506, 31), intArrayOf(511, 31)),
        arrayOf(
            intArrayOf(497, 72), intArrayOf(497, 63), intArrayOf(497, 72), intArrayOf(499, 72), intArrayOf(499, 65),
            intArrayOf(499, 72), intArrayOf(501, 72), intArrayOf(501, 71), intArrayOf(501, 72), intArrayOf(503, 72),
            intArrayOf(503, 67), intArrayOf(503, 72), intArrayOf(505, 72), intArrayOf(505, 63), intArrayOf(505, 72),
            intArrayOf(507, 72), intArrayOf(507, 71), intArrayOf(507, 72), intArrayOf(509, 72), intArrayOf(509, 70),
            intArrayOf(509, 72), intArrayOf(511, 72), intArrayOf(511, 69), intArrayOf(511, 72)
        ),
        arrayOf(
            intArrayOf(510, 75), intArrayOf(510, 78), intArrayOf(505, 78), intArrayOf(505, 84), intArrayOf(518, 84),
            intArrayOf(518, 78), intArrayOf(515, 78), intArrayOf(515, 75)
        ),
        arrayOf(
            intArrayOf(513, 97), intArrayOf(513, 89), intArrayOf(513, 97), intArrayOf(515, 97), intArrayOf(515, 94),
            intArrayOf(515, 97), intArrayOf(517, 97), intArrayOf(517, 94), intArrayOf(517, 97), intArrayOf(519, 97),
            intArrayOf(519, 93), intArrayOf(519, 97), intArrayOf(521, 97), intArrayOf(521, 88), intArrayOf(521, 97),
            intArrayOf(523, 97), intArrayOf(523, 94), intArrayOf(523, 97)
        ),
        arrayOf(intArrayOf(507, 117), intArrayOf(521, 117), intArrayOf(521, 116)),
        arrayOf(
            intArrayOf(488, 59), intArrayOf(488, 51), intArrayOf(488, 59), intArrayOf(490, 59), intArrayOf(490, 52),
            intArrayOf(490, 59), intArrayOf(492, 59), intArrayOf(492, 55), intArrayOf(492, 59), intArrayOf(494, 59),
            intArrayOf(494, 52), intArrayOf(494, 59), intArrayOf(496, 59), intArrayOf(496, 50), intArrayOf(496, 59),
            intArrayOf(498, 59), intArrayOf(498, 58), intArrayOf(498, 59), intArrayOf(500, 59), intArrayOf(500, 56),
            intArrayOf(500, 59), intArrayOf(502, 59), intArrayOf(502, 56), intArrayOf(502, 59), intArrayOf(504, 59),
            intArrayOf(504, 57), intArrayOf(504, 59)
        ),
        arrayOf(intArrayOf(534, 152), intArrayOf(539, 152)),
        arrayOf(
            intArrayOf(513, 97), intArrayOf(513, 89), intArrayOf(513, 97), intArrayOf(515, 97), intArrayOf(515, 94),
            intArrayOf(515, 97), intArrayOf(517, 97), intArrayOf(517, 94), intArrayOf(517, 97), intArrayOf(519, 97),
            intArrayOf(519, 93), intArrayOf(519, 97), intArrayOf(521, 97), intArrayOf(521, 88), intArrayOf(521, 97),
            intArrayOf(523, 97), intArrayOf(523, 94), intArrayOf(523, 97)
        ),
        arrayOf(
            intArrayOf(488, 59), intArrayOf(488, 51), intArrayOf(488, 59), intArrayOf(490, 59), intArrayOf(490, 52),
            intArrayOf(490, 59), intArrayOf(492, 59), intArrayOf(492, 55), intArrayOf(492, 59), intArrayOf(494, 59),
            intArrayOf(494, 52), intArrayOf(494, 59), intArrayOf(496, 59), intArrayOf(496, 50), intArrayOf(496, 59),
            intArrayOf(498, 59), intArrayOf(498, 58), intArrayOf(498, 59), intArrayOf(500, 59), intArrayOf(500, 56),
            intArrayOf(500, 59), intArrayOf(502, 59), intArrayOf(502, 56), intArrayOf(502, 59), intArrayOf(504, 59),
            intArrayOf(504, 57), intArrayOf(504, 59)
        ),
        arrayOf(
            intArrayOf(497, 72), intArrayOf(497, 63), intArrayOf(497, 72), intArrayOf(499, 72), intArrayOf(499, 65),
            intArrayOf(499, 72), intArrayOf(501, 72), intArrayOf(501, 71), intArrayOf(501, 72), intArrayOf(503, 72),
            intArrayOf(503, 67), intArrayOf(503, 72), intArrayOf(505, 72), intArrayOf(505, 63), intArrayOf(505, 72),
            intArrayOf(507, 72), intArrayOf(507, 71), intArrayOf(507, 72), intArrayOf(509, 72), intArrayOf(509, 70),
            intArrayOf(509, 72), intArrayOf(511, 72), intArrayOf(511, 69), intArrayOf(511, 72)
        ),
        arrayOf(intArrayOf(495, 19), intArrayOf(500, 19)),
        arrayOf(
            intArrayOf(488, 59), intArrayOf(488, 51), intArrayOf(488, 59), intArrayOf(490, 59), intArrayOf(490, 52),
            intArrayOf(490, 59), intArrayOf(492, 59), intArrayOf(492, 55), intArrayOf(492, 59), intArrayOf(494, 59),
            intArrayOf(494, 52), intArrayOf(494, 59), intArrayOf(496, 59), intArrayOf(496, 50), intArrayOf(496, 59),
            intArrayOf(498, 59), intArrayOf(498, 58), intArrayOf(498, 59), intArrayOf(500, 59), intArrayOf(500, 56),
            intArrayOf(500, 59), intArrayOf(502, 59), intArrayOf(502, 56), intArrayOf(502, 59), intArrayOf(504, 59),
            intArrayOf(504, 57), intArrayOf(504, 59)
        ),
        arrayOf(
            intArrayOf(513, 97), intArrayOf(513, 89), intArrayOf(513, 97), intArrayOf(515, 97), intArrayOf(515, 94),
            intArrayOf(515, 97), intArrayOf(517, 97), intArrayOf(517, 94), intArrayOf(517, 97), intArrayOf(519, 97),
            intArrayOf(519, 93), intArrayOf(519, 97), intArrayOf(521, 97), intArrayOf(521, 88), intArrayOf(521, 97),
            intArrayOf(523, 97), intArrayOf(523, 94), intArrayOf(523, 97)
        ),
        arrayOf(
            intArrayOf(497, 72), intArrayOf(497, 63), intArrayOf(497, 72), intArrayOf(499, 72), intArrayOf(499, 65),
            intArrayOf(499, 72), intArrayOf(501, 72), intArrayOf(501, 71), intArrayOf(501, 72), intArrayOf(503, 72),
            intArrayOf(503, 67), intArrayOf(503, 72), intArrayOf(505, 72), intArrayOf(505, 63), intArrayOf(505, 72),
            intArrayOf(507, 72), intArrayOf(507, 71), intArrayOf(507, 72), intArrayOf(509, 72), intArrayOf(509, 70),
            intArrayOf(509, 72), intArrayOf(511, 72), intArrayOf(511, 69), intArrayOf(511, 72)
        ),
        arrayOf(
            intArrayOf(513, 97), intArrayOf(513, 89), intArrayOf(513, 97), intArrayOf(515, 97), intArrayOf(515, 94),
            intArrayOf(515, 97), intArrayOf(517, 97), intArrayOf(517, 94), intArrayOf(517, 97), intArrayOf(519, 97),
            intArrayOf(519, 93), intArrayOf(519, 97), intArrayOf(521, 97), intArrayOf(521, 88), intArrayOf(521, 97),
            intArrayOf(523, 97), intArrayOf(523, 94), intArrayOf(523, 97)
        ),
        arrayOf(
            intArrayOf(523, 136), intArrayOf(523, 138), intArrayOf(518, 138), intArrayOf(518, 145),
            intArrayOf(535, 145), intArrayOf(535, 138), intArrayOf(528, 138), intArrayOf(528, 136)
        ),
        arrayOf(
            intArrayOf(497, 72), intArrayOf(497, 63), intArrayOf(497, 72), intArrayOf(499, 72), intArrayOf(499, 65),
            intArrayOf(499, 72), intArrayOf(501, 72), intArrayOf(501, 71), intArrayOf(501, 72), intArrayOf(503, 72),
            intArrayOf(503, 67), intArrayOf(503, 72), intArrayOf(505, 72), intArrayOf(505, 63), intArrayOf(505, 72),
            intArrayOf(507, 72), intArrayOf(507, 71), intArrayOf(507, 72), intArrayOf(509, 72), intArrayOf(509, 70),
            intArrayOf(509, 72), intArrayOf(511, 72), intArrayOf(511, 69), intArrayOf(511, 72)
        ),
        arrayOf(intArrayOf(527, 152), intArrayOf(532, 152)),
        arrayOf(
            intArrayOf(513, 97), intArrayOf(513, 89), intArrayOf(513, 97), intArrayOf(515, 97), intArrayOf(515, 94),
            intArrayOf(515, 97), intArrayOf(517, 97), intArrayOf(517, 94), intArrayOf(517, 97), intArrayOf(519, 97),
            intArrayOf(519, 93), intArrayOf(519, 97), intArrayOf(521, 97), intArrayOf(521, 88), intArrayOf(521, 97),
            intArrayOf(523, 97), intArrayOf(523, 94), intArrayOf(523, 97)
        ),
        arrayOf(intArrayOf(533, 148), intArrayOf(538, 148)),
        arrayOf(
            intArrayOf(510, 75), intArrayOf(510, 78), intArrayOf(505, 78), intArrayOf(505, 84), intArrayOf(518, 84),
            intArrayOf(518, 78), intArrayOf(515, 78), intArrayOf(515, 75)
        ),
        arrayOf(
            intArrayOf(488, 59), intArrayOf(488, 51), intArrayOf(488, 59), intArrayOf(490, 59), intArrayOf(490, 52),
            intArrayOf(490, 59), intArrayOf(492, 59), intArrayOf(492, 55), intArrayOf(492, 59), intArrayOf(494, 59),
            intArrayOf(494, 52), intArrayOf(494, 59), intArrayOf(496, 59), intArrayOf(496, 50), intArrayOf(496, 59),
            intArrayOf(498, 59), intArrayOf(498, 58), intArrayOf(498, 59), intArrayOf(500, 59), intArrayOf(500, 56),
            intArrayOf(500, 59), intArrayOf(502, 59), intArrayOf(502, 56), intArrayOf(502, 59), intArrayOf(504, 59),
            intArrayOf(504, 57), intArrayOf(504, 59)
        ),
        arrayOf(intArrayOf(516, 46), intArrayOf(520, 46)),
        arrayOf(
            intArrayOf(497, 72), intArrayOf(497, 63), intArrayOf(497, 72), intArrayOf(499, 72), intArrayOf(499, 65),
            intArrayOf(499, 72), intArrayOf(501, 72), intArrayOf(501, 71), intArrayOf(501, 72), intArrayOf(503, 72),
            intArrayOf(503, 67), intArrayOf(503, 72), intArrayOf(505, 72), intArrayOf(505, 63), intArrayOf(505, 72),
            intArrayOf(507, 72), intArrayOf(507, 71), intArrayOf(507, 72), intArrayOf(509, 72), intArrayOf(509, 70),
            intArrayOf(509, 72), intArrayOf(511, 72), intArrayOf(511, 69), intArrayOf(511, 72)
        ),
        arrayOf(
            intArrayOf(497, 72), intArrayOf(497, 63), intArrayOf(497, 72), intArrayOf(499, 72), intArrayOf(499, 65),
            intArrayOf(499, 72), intArrayOf(501, 72), intArrayOf(501, 71), intArrayOf(501, 72), intArrayOf(503, 72),
            intArrayOf(503, 67), intArrayOf(503, 72), intArrayOf(505, 72), intArrayOf(505, 63), intArrayOf(505, 72),
            intArrayOf(507, 72), intArrayOf(507, 71), intArrayOf(507, 72), intArrayOf(509, 72), intArrayOf(509, 70),
            intArrayOf(509, 72), intArrayOf(511, 72), intArrayOf(511, 69), intArrayOf(511, 72)
        ),
        arrayOf(
            intArrayOf(545, 155), intArrayOf(545, 157), intArrayOf(541, 157), intArrayOf(541, 160),
            intArrayOf(556, 160), intArrayOf(556, 157), intArrayOf(550, 157), intArrayOf(550, 155)
        ),
        arrayOf(intArrayOf(501, 119), intArrayOf(501, 120), intArrayOf(512, 120), intArrayOf(512, 119)),
        arrayOf(
            intArrayOf(513, 97), intArrayOf(513, 89), intArrayOf(513, 97), intArrayOf(515, 97), intArrayOf(515, 94),
            intArrayOf(515, 97), intArrayOf(517, 97), intArrayOf(517, 94), intArrayOf(517, 97), intArrayOf(519, 97),
            intArrayOf(519, 93), intArrayOf(519, 97), intArrayOf(521, 97), intArrayOf(521, 88), intArrayOf(521, 97),
            intArrayOf(523, 97), intArrayOf(523, 94), intArrayOf(523, 97)
        ),
        arrayOf(
            intArrayOf(488, 59), intArrayOf(488, 51), intArrayOf(488, 59), intArrayOf(490, 59), intArrayOf(490, 52),
            intArrayOf(490, 59), intArrayOf(492, 59), intArrayOf(492, 55), intArrayOf(492, 59), intArrayOf(494, 59),
            intArrayOf(494, 52), intArrayOf(494, 59), intArrayOf(496, 59), intArrayOf(496, 50), intArrayOf(496, 59),
            intArrayOf(498, 59), intArrayOf(498, 58), intArrayOf(498, 59), intArrayOf(500, 59), intArrayOf(500, 56),
            intArrayOf(500, 59), intArrayOf(502, 59), intArrayOf(502, 56), intArrayOf(502, 59), intArrayOf(504, 59),
            intArrayOf(504, 57), intArrayOf(504, 59)
        ),
        arrayOf(intArrayOf(512, 25), intArrayOf(517, 25)),
        arrayOf(
            intArrayOf(526, 133), intArrayOf(526, 131), intArrayOf(526, 133), intArrayOf(528, 133),
            intArrayOf(528, 129), intArrayOf(528, 133), intArrayOf(530, 133), intArrayOf(530, 129), intArrayOf(530, 133)
        ),
        arrayOf(
            intArrayOf(497, 72), intArrayOf(497, 63), intArrayOf(497, 72), intArrayOf(499, 72), intArrayOf(499, 65),
            intArrayOf(499, 72), intArrayOf(501, 72), intArrayOf(501, 71), intArrayOf(501, 72), intArrayOf(503, 72),
            intArrayOf(503, 67), intArrayOf(503, 72), intArrayOf(505, 72), intArrayOf(505, 63), intArrayOf(505, 72),
            intArrayOf(507, 72), intArrayOf(507, 71), intArrayOf(507, 72), intArrayOf(509, 72), intArrayOf(509, 70),
            intArrayOf(509, 72), intArrayOf(511, 72), intArrayOf(511, 69), intArrayOf(511, 72)
        ),
        arrayOf(
            intArrayOf(513, 97), intArrayOf(513, 89), intArrayOf(513, 97), intArrayOf(515, 97), intArrayOf(515, 94),
            intArrayOf(515, 97), intArrayOf(517, 97), intArrayOf(517, 94), intArrayOf(517, 97), intArrayOf(519, 97),
            intArrayOf(519, 93), intArrayOf(519, 97), intArrayOf(521, 97), intArrayOf(521, 88), intArrayOf(521, 97),
            intArrayOf(523, 97), intArrayOf(523, 94), intArrayOf(523, 97)
        ),
        arrayOf(intArrayOf(513, 31), intArrayOf(518, 31)),
        arrayOf(
            intArrayOf(526, 133), intArrayOf(526, 131), intArrayOf(526, 133), intArrayOf(528, 133),
            intArrayOf(528, 129), intArrayOf(528, 133), intArrayOf(530, 133), intArrayOf(530, 129), intArrayOf(530, 133)
        ),
        arrayOf(
            intArrayOf(488, 59), intArrayOf(488, 51), intArrayOf(488, 59), intArrayOf(490, 59), intArrayOf(490, 52),
            intArrayOf(490, 59), intArrayOf(492, 59), intArrayOf(492, 55), intArrayOf(492, 59), intArrayOf(494, 59),
            intArrayOf(494, 52), intArrayOf(494, 59), intArrayOf(496, 59), intArrayOf(496, 50), intArrayOf(496, 59),
            intArrayOf(498, 59), intArrayOf(498, 58), intArrayOf(498, 59), intArrayOf(500, 59), intArrayOf(500, 56),
            intArrayOf(500, 59), intArrayOf(502, 59), intArrayOf(502, 56), intArrayOf(502, 59), intArrayOf(504, 59),
            intArrayOf(504, 57), intArrayOf(504, 59)
        ),
        arrayOf(intArrayOf(520, 31), intArrayOf(525, 31)),
        arrayOf(
            intArrayOf(497, 72), intArrayOf(497, 63), intArrayOf(497, 72), intArrayOf(499, 72), intArrayOf(499, 65),
            intArrayOf(499, 72), intArrayOf(501, 72), intArrayOf(501, 71), intArrayOf(501, 72), intArrayOf(503, 72),
            intArrayOf(503, 67), intArrayOf(503, 72), intArrayOf(505, 72), intArrayOf(505, 63), intArrayOf(505, 72),
            intArrayOf(507, 72), intArrayOf(507, 71), intArrayOf(507, 72), intArrayOf(509, 72), intArrayOf(509, 70),
            intArrayOf(509, 72), intArrayOf(511, 72), intArrayOf(511, 69), intArrayOf(511, 72)
        ),
        arrayOf(intArrayOf(482, 21), intArrayOf(482, 22), intArrayOf(490, 22)),
        arrayOf(
            intArrayOf(510, 75), intArrayOf(510, 78), intArrayOf(505, 78), intArrayOf(505, 84), intArrayOf(518, 84),
            intArrayOf(518, 78), intArrayOf(515, 78), intArrayOf(515, 75)
        ),
        arrayOf(intArrayOf(498, 17), intArrayOf(503, 17)),
        arrayOf(
            intArrayOf(523, 136), intArrayOf(523, 138), intArrayOf(518, 138), intArrayOf(518, 145),
            intArrayOf(535, 145), intArrayOf(535, 138), intArrayOf(528, 138), intArrayOf(528, 136)
        ),
        arrayOf(
            intArrayOf(545, 155), intArrayOf(545, 157), intArrayOf(541, 157), intArrayOf(541, 160),
            intArrayOf(556, 160), intArrayOf(556, 157), intArrayOf(550, 157), intArrayOf(550, 155)
        ),
        arrayOf(intArrayOf(513, 43), intArrayOf(517, 43)),
        arrayOf(
            intArrayOf(523, 100), intArrayOf(523, 104), intArrayOf(519, 104), intArrayOf(519, 111),
            intArrayOf(528, 111), intArrayOf(528, 104), intArrayOf(526, 104), intArrayOf(526, 100)
        ),
        arrayOf(intArrayOf(505, 17), intArrayOf(510, 17)),
        arrayOf(
            intArrayOf(497, 72), intArrayOf(497, 63), intArrayOf(497, 72), intArrayOf(499, 72), intArrayOf(499, 65),
            intArrayOf(499, 72), intArrayOf(501, 72), intArrayOf(501, 71), intArrayOf(501, 72), intArrayOf(503, 72),
            intArrayOf(503, 67), intArrayOf(503, 72), intArrayOf(505, 72), intArrayOf(505, 63), intArrayOf(505, 72),
            intArrayOf(507, 72), intArrayOf(507, 71), intArrayOf(507, 72), intArrayOf(509, 72), intArrayOf(509, 70),
            intArrayOf(509, 72), intArrayOf(511, 72), intArrayOf(511, 69), intArrayOf(511, 72)
        ),
        arrayOf(intArrayOf(509, 28), intArrayOf(514, 28)),
        arrayOf(
            intArrayOf(545, 155), intArrayOf(545, 157), intArrayOf(541, 157), intArrayOf(541, 160),
            intArrayOf(556, 160), intArrayOf(556, 157), intArrayOf(550, 157), intArrayOf(550, 155)
        ),
        arrayOf(intArrayOf(537, 150), intArrayOf(542, 150)),
        arrayOf(
            intArrayOf(497, 72), intArrayOf(497, 63), intArrayOf(497, 72), intArrayOf(499, 72), intArrayOf(499, 65),
            intArrayOf(499, 72), intArrayOf(501, 72), intArrayOf(501, 71), intArrayOf(501, 72), intArrayOf(503, 72),
            intArrayOf(503, 67), intArrayOf(503, 72), intArrayOf(505, 72), intArrayOf(505, 63), intArrayOf(505, 72),
            intArrayOf(507, 72), intArrayOf(507, 71), intArrayOf(507, 72), intArrayOf(509, 72), intArrayOf(509, 70),
            intArrayOf(509, 72), intArrayOf(511, 72), intArrayOf(511, 69), intArrayOf(511, 72)
        ),
        arrayOf(intArrayOf(501, 43), intArrayOf(505, 43)),
        arrayOf(
            intArrayOf(513, 97), intArrayOf(513, 89), intArrayOf(513, 97), intArrayOf(515, 97), intArrayOf(515, 94),
            intArrayOf(515, 97), intArrayOf(517, 97), intArrayOf(517, 94), intArrayOf(517, 97), intArrayOf(519, 97),
            intArrayOf(519, 93), intArrayOf(519, 97), intArrayOf(521, 97), intArrayOf(521, 88), intArrayOf(521, 97),
            intArrayOf(523, 97), intArrayOf(523, 94), intArrayOf(523, 97)
        ),
        arrayOf(
            intArrayOf(488, 59), intArrayOf(488, 51), intArrayOf(488, 59), intArrayOf(490, 59), intArrayOf(490, 52),
            intArrayOf(490, 59), intArrayOf(492, 59), intArrayOf(492, 55), intArrayOf(492, 59), intArrayOf(494, 59),
            intArrayOf(494, 52), intArrayOf(494, 59), intArrayOf(496, 59), intArrayOf(496, 50), intArrayOf(496, 59),
            intArrayOf(498, 59), intArrayOf(498, 58), intArrayOf(498, 59), intArrayOf(500, 59), intArrayOf(500, 56),
            intArrayOf(500, 59), intArrayOf(502, 59), intArrayOf(502, 56), intArrayOf(502, 59), intArrayOf(504, 59),
            intArrayOf(504, 57), intArrayOf(504, 59)
        ),
        arrayOf(
            intArrayOf(497, 72), intArrayOf(497, 63), intArrayOf(497, 72), intArrayOf(499, 72), intArrayOf(499, 65),
            intArrayOf(499, 72), intArrayOf(501, 72), intArrayOf(501, 71), intArrayOf(501, 72), intArrayOf(503, 72),
            intArrayOf(503, 67), intArrayOf(503, 72), intArrayOf(505, 72), intArrayOf(505, 63), intArrayOf(505, 72),
            intArrayOf(507, 72), intArrayOf(507, 71), intArrayOf(507, 72), intArrayOf(509, 72), intArrayOf(509, 70),
            intArrayOf(509, 72), intArrayOf(511, 72), intArrayOf(511, 69), intArrayOf(511, 72)
        ),
        arrayOf(intArrayOf(501, 119), intArrayOf(501, 120), intArrayOf(512, 120), intArrayOf(512, 119)),
        arrayOf(intArrayOf(507, 37), intArrayOf(511, 37)),
        arrayOf(
            intArrayOf(497, 72), intArrayOf(497, 63), intArrayOf(497, 72), intArrayOf(499, 72), intArrayOf(499, 65),
            intArrayOf(499, 72), intArrayOf(501, 72), intArrayOf(501, 71), intArrayOf(501, 72), intArrayOf(503, 72),
            intArrayOf(503, 67), intArrayOf(503, 72), intArrayOf(505, 72), intArrayOf(505, 63), intArrayOf(505, 72),
            intArrayOf(507, 72), intArrayOf(507, 71), intArrayOf(507, 72), intArrayOf(509, 72), intArrayOf(509, 70),
            intArrayOf(509, 72), intArrayOf(511, 72), intArrayOf(511, 69), intArrayOf(511, 72)
        ),
        arrayOf(
            intArrayOf(488, 59), intArrayOf(488, 51), intArrayOf(488, 59), intArrayOf(490, 59), intArrayOf(490, 52),
            intArrayOf(490, 59), intArrayOf(492, 59), intArrayOf(492, 55), intArrayOf(492, 59), intArrayOf(494, 59),
            intArrayOf(494, 52), intArrayOf(494, 59), intArrayOf(496, 59), intArrayOf(496, 50), intArrayOf(496, 59),
            intArrayOf(498, 59), intArrayOf(498, 58), intArrayOf(498, 59), intArrayOf(500, 59), intArrayOf(500, 56),
            intArrayOf(500, 59), intArrayOf(502, 59), intArrayOf(502, 56), intArrayOf(502, 59), intArrayOf(504, 59),
            intArrayOf(504, 57), intArrayOf(504, 59)
        ),
        arrayOf(
            intArrayOf(523, 100), intArrayOf(523, 104), intArrayOf(519, 104), intArrayOf(519, 111),
            intArrayOf(528, 111), intArrayOf(528, 104), intArrayOf(526, 104), intArrayOf(526, 100)
        ),
        arrayOf(
            intArrayOf(488, 59), intArrayOf(488, 51), intArrayOf(488, 59), intArrayOf(490, 59), intArrayOf(490, 52),
            intArrayOf(490, 59), intArrayOf(492, 59), intArrayOf(492, 55), intArrayOf(492, 59), intArrayOf(494, 59),
            intArrayOf(494, 52), intArrayOf(494, 59), intArrayOf(496, 59), intArrayOf(496, 50), intArrayOf(496, 59),
            intArrayOf(498, 59), intArrayOf(498, 58), intArrayOf(498, 59), intArrayOf(500, 59), intArrayOf(500, 56),
            intArrayOf(500, 59), intArrayOf(502, 59), intArrayOf(502, 56), intArrayOf(502, 59), intArrayOf(504, 59),
            intArrayOf(504, 57), intArrayOf(504, 59)
        ),
        arrayOf(
            intArrayOf(513, 97), intArrayOf(513, 89), intArrayOf(513, 97), intArrayOf(515, 97), intArrayOf(515, 94),
            intArrayOf(515, 97), intArrayOf(517, 97), intArrayOf(517, 94), intArrayOf(517, 97), intArrayOf(519, 97),
            intArrayOf(519, 93), intArrayOf(519, 97), intArrayOf(521, 97), intArrayOf(521, 88), intArrayOf(521, 97),
            intArrayOf(523, 97), intArrayOf(523, 94), intArrayOf(523, 97)
        ),
        arrayOf(intArrayOf(501, 119), intArrayOf(501, 120), intArrayOf(512, 120), intArrayOf(512, 119)),
        arrayOf(intArrayOf(510, 40), intArrayOf(514, 40)),
        arrayOf(intArrayOf(488, 19), intArrayOf(493, 19)),
        arrayOf(intArrayOf(482, 21), intArrayOf(482, 22), intArrayOf(490, 22)),
        arrayOf(intArrayOf(494, 15), intArrayOf(499, 15)),
        arrayOf(
            intArrayOf(523, 136), intArrayOf(523, 138), intArrayOf(518, 138), intArrayOf(518, 145),
            intArrayOf(535, 145), intArrayOf(535, 138), intArrayOf(528, 138), intArrayOf(528, 136)
        ),
        arrayOf(
            intArrayOf(526, 133), intArrayOf(526, 131), intArrayOf(526, 133), intArrayOf(528, 133),
            intArrayOf(528, 129), intArrayOf(528, 133), intArrayOf(530, 133), intArrayOf(530, 129), intArrayOf(530, 133)
        ),
        arrayOf(
            intArrayOf(513, 97), intArrayOf(513, 89), intArrayOf(513, 97), intArrayOf(515, 97), intArrayOf(515, 94),
            intArrayOf(515, 97), intArrayOf(517, 97), intArrayOf(517, 94), intArrayOf(517, 97), intArrayOf(519, 97),
            intArrayOf(519, 93), intArrayOf(519, 97), intArrayOf(521, 97), intArrayOf(521, 88), intArrayOf(521, 97),
            intArrayOf(523, 97), intArrayOf(523, 94), intArrayOf(523, 97)
        ),
        arrayOf(
            intArrayOf(488, 59), intArrayOf(488, 51), intArrayOf(488, 59), intArrayOf(490, 59), intArrayOf(490, 52),
            intArrayOf(490, 59), intArrayOf(492, 59), intArrayOf(492, 55), intArrayOf(492, 59), intArrayOf(494, 59),
            intArrayOf(494, 52), intArrayOf(494, 59), intArrayOf(496, 59), intArrayOf(496, 50), intArrayOf(496, 59),
            intArrayOf(498, 59), intArrayOf(498, 58), intArrayOf(498, 59), intArrayOf(500, 59), intArrayOf(500, 56),
            intArrayOf(500, 59), intArrayOf(502, 59), intArrayOf(502, 56), intArrayOf(502, 59), intArrayOf(504, 59),
            intArrayOf(504, 57), intArrayOf(504, 59)
        ),
        arrayOf(
            intArrayOf(488, 59), intArrayOf(488, 51), intArrayOf(488, 59), intArrayOf(490, 59), intArrayOf(490, 52),
            intArrayOf(490, 59), intArrayOf(492, 59), intArrayOf(492, 55), intArrayOf(492, 59), intArrayOf(494, 59),
            intArrayOf(494, 52), intArrayOf(494, 59), intArrayOf(496, 59), intArrayOf(496, 50), intArrayOf(496, 59),
            intArrayOf(498, 59), intArrayOf(498, 58), intArrayOf(498, 59), intArrayOf(500, 59), intArrayOf(500, 56),
            intArrayOf(500, 59), intArrayOf(502, 59), intArrayOf(502, 56), intArrayOf(502, 59), intArrayOf(504, 59),
            intArrayOf(504, 57), intArrayOf(504, 59)
        ),
        arrayOf(
            intArrayOf(488, 59), intArrayOf(488, 51), intArrayOf(488, 59), intArrayOf(490, 59), intArrayOf(490, 52),
            intArrayOf(490, 59), intArrayOf(492, 59), intArrayOf(492, 55), intArrayOf(492, 59), intArrayOf(494, 59),
            intArrayOf(494, 52), intArrayOf(494, 59), intArrayOf(496, 59), intArrayOf(496, 50), intArrayOf(496, 59),
            intArrayOf(498, 59), intArrayOf(498, 58), intArrayOf(498, 59), intArrayOf(500, 59), intArrayOf(500, 56),
            intArrayOf(500, 59), intArrayOf(502, 59), intArrayOf(502, 56), intArrayOf(502, 59), intArrayOf(504, 59),
            intArrayOf(504, 57), intArrayOf(504, 59)
        ),
        arrayOf(
            intArrayOf(526, 133), intArrayOf(526, 131), intArrayOf(526, 133), intArrayOf(528, 133),
            intArrayOf(528, 129), intArrayOf(528, 133), intArrayOf(530, 133), intArrayOf(530, 129), intArrayOf(530, 133)
        ),
        arrayOf(
            intArrayOf(513, 97), intArrayOf(513, 89), intArrayOf(513, 97), intArrayOf(515, 97), intArrayOf(515, 94),
            intArrayOf(515, 97), intArrayOf(517, 97), intArrayOf(517, 94), intArrayOf(517, 97), intArrayOf(519, 97),
            intArrayOf(519, 93), intArrayOf(519, 97), intArrayOf(521, 97), intArrayOf(521, 88), intArrayOf(521, 97),
            intArrayOf(523, 97), intArrayOf(523, 94), intArrayOf(523, 97)
        ),
        arrayOf(
            intArrayOf(488, 59), intArrayOf(488, 51), intArrayOf(488, 59), intArrayOf(490, 59), intArrayOf(490, 52),
            intArrayOf(490, 59), intArrayOf(492, 59), intArrayOf(492, 55), intArrayOf(492, 59), intArrayOf(494, 59),
            intArrayOf(494, 52), intArrayOf(494, 59), intArrayOf(496, 59), intArrayOf(496, 50), intArrayOf(496, 59),
            intArrayOf(498, 59), intArrayOf(498, 58), intArrayOf(498, 59), intArrayOf(500, 59), intArrayOf(500, 56),
            intArrayOf(500, 59), intArrayOf(502, 59), intArrayOf(502, 56), intArrayOf(502, 59), intArrayOf(504, 59),
            intArrayOf(504, 57), intArrayOf(504, 59)
        ),
        arrayOf(
            intArrayOf(545, 155), intArrayOf(545, 157), intArrayOf(541, 157), intArrayOf(541, 160),
            intArrayOf(556, 160), intArrayOf(556, 157), intArrayOf(550, 157), intArrayOf(550, 155)
        ),
        arrayOf(
            intArrayOf(513, 97), intArrayOf(513, 89), intArrayOf(513, 97), intArrayOf(515, 97), intArrayOf(515, 94),
            intArrayOf(515, 97), intArrayOf(517, 97), intArrayOf(517, 94), intArrayOf(517, 97), intArrayOf(519, 97),
            intArrayOf(519, 93), intArrayOf(519, 97), intArrayOf(521, 97), intArrayOf(521, 88), intArrayOf(521, 97),
            intArrayOf(523, 97), intArrayOf(523, 94), intArrayOf(523, 97)
        ),
        arrayOf(
            intArrayOf(523, 136), intArrayOf(523, 138), intArrayOf(518, 138), intArrayOf(518, 145),
            intArrayOf(535, 145), intArrayOf(535, 138), intArrayOf(528, 138), intArrayOf(528, 136)
        ),
        arrayOf(intArrayOf(504, 40), intArrayOf(508, 40)),
        arrayOf(
            intArrayOf(497, 72), intArrayOf(497, 63), intArrayOf(497, 72), intArrayOf(499, 72), intArrayOf(499, 65),
            intArrayOf(499, 72), intArrayOf(501, 72), intArrayOf(501, 71), intArrayOf(501, 72), intArrayOf(503, 72),
            intArrayOf(503, 67), intArrayOf(503, 72), intArrayOf(505, 72), intArrayOf(505, 63), intArrayOf(505, 72),
            intArrayOf(507, 72), intArrayOf(507, 71), intArrayOf(507, 72), intArrayOf(509, 72), intArrayOf(509, 70),
            intArrayOf(509, 72), intArrayOf(511, 72), intArrayOf(511, 69), intArrayOf(511, 72)
        ),
        arrayOf(
            intArrayOf(526, 133), intArrayOf(526, 131), intArrayOf(526, 133), intArrayOf(528, 133),
            intArrayOf(528, 129), intArrayOf(528, 133), intArrayOf(530, 133), intArrayOf(530, 129), intArrayOf(530, 133)
        ),
        arrayOf(
            intArrayOf(523, 100), intArrayOf(523, 104), intArrayOf(519, 104), intArrayOf(519, 111),
            intArrayOf(528, 111), intArrayOf(528, 104), intArrayOf(526, 104), intArrayOf(526, 100)
        ),
        arrayOf(
            intArrayOf(545, 155), intArrayOf(545, 157), intArrayOf(541, 157), intArrayOf(541, 160),
            intArrayOf(556, 160), intArrayOf(556, 157), intArrayOf(550, 157), intArrayOf(550, 155)
        ),
        arrayOf(intArrayOf(516, 28), intArrayOf(521, 28)),
        arrayOf(
            intArrayOf(545, 155), intArrayOf(545, 157), intArrayOf(541, 157), intArrayOf(541, 160),
            intArrayOf(556, 160), intArrayOf(556, 157), intArrayOf(550, 157), intArrayOf(550, 155)
        ),
        arrayOf(
            intArrayOf(497, 72), intArrayOf(497, 63), intArrayOf(497, 72), intArrayOf(499, 72), intArrayOf(499, 65),
            intArrayOf(499, 72), intArrayOf(501, 72), intArrayOf(501, 71), intArrayOf(501, 72), intArrayOf(503, 72),
            intArrayOf(503, 67), intArrayOf(503, 72), intArrayOf(505, 72), intArrayOf(505, 63), intArrayOf(505, 72),
            intArrayOf(507, 72), intArrayOf(507, 71), intArrayOf(507, 72), intArrayOf(509, 72), intArrayOf(509, 70),
            intArrayOf(509, 72), intArrayOf(511, 72), intArrayOf(511, 69), intArrayOf(511, 72)
        ),
        arrayOf(
            intArrayOf(497, 72), intArrayOf(497, 63), intArrayOf(497, 72), intArrayOf(499, 72), intArrayOf(499, 65),
            intArrayOf(499, 72), intArrayOf(501, 72), intArrayOf(501, 71), intArrayOf(501, 72), intArrayOf(503, 72),
            intArrayOf(503, 67), intArrayOf(503, 72), intArrayOf(505, 72), intArrayOf(505, 63), intArrayOf(505, 72),
            intArrayOf(507, 72), intArrayOf(507, 71), intArrayOf(507, 72), intArrayOf(509, 72), intArrayOf(509, 70),
            intArrayOf(509, 72), intArrayOf(511, 72), intArrayOf(511, 69), intArrayOf(511, 72)
        ),
        arrayOf(
            intArrayOf(488, 59), intArrayOf(488, 51), intArrayOf(488, 59), intArrayOf(490, 59), intArrayOf(490, 52),
            intArrayOf(490, 59), intArrayOf(492, 59), intArrayOf(492, 55), intArrayOf(492, 59), intArrayOf(494, 59),
            intArrayOf(494, 52), intArrayOf(494, 59), intArrayOf(496, 59), intArrayOf(496, 50), intArrayOf(496, 59),
            intArrayOf(498, 59), intArrayOf(498, 58), intArrayOf(498, 59), intArrayOf(500, 59), intArrayOf(500, 56),
            intArrayOf(500, 59), intArrayOf(502, 59), intArrayOf(502, 56), intArrayOf(502, 59), intArrayOf(504, 59),
            intArrayOf(504, 57), intArrayOf(504, 59)
        ),
        arrayOf(intArrayOf(491, 17), intArrayOf(496, 17)),
        arrayOf(
            intArrayOf(488, 59), intArrayOf(488, 51), intArrayOf(488, 59), intArrayOf(490, 59), intArrayOf(490, 52),
            intArrayOf(490, 59), intArrayOf(492, 59), intArrayOf(492, 55), intArrayOf(492, 59), intArrayOf(494, 59),
            intArrayOf(494, 52), intArrayOf(494, 59), intArrayOf(496, 59), intArrayOf(496, 50), intArrayOf(496, 59),
            intArrayOf(498, 59), intArrayOf(498, 58), intArrayOf(498, 59), intArrayOf(500, 59), intArrayOf(500, 56),
            intArrayOf(500, 59), intArrayOf(502, 59), intArrayOf(502, 56), intArrayOf(502, 59), intArrayOf(504, 59),
            intArrayOf(504, 57), intArrayOf(504, 59)
        ),
        arrayOf(
            intArrayOf(526, 133), intArrayOf(526, 131), intArrayOf(526, 133), intArrayOf(528, 133),
            intArrayOf(528, 129), intArrayOf(528, 133), intArrayOf(530, 133), intArrayOf(530, 129), intArrayOf(530, 133)
        ),
        arrayOf(
            intArrayOf(497, 72), intArrayOf(497, 63), intArrayOf(497, 72), intArrayOf(499, 72), intArrayOf(499, 65),
            intArrayOf(499, 72), intArrayOf(501, 72), intArrayOf(501, 71), intArrayOf(501, 72), intArrayOf(503, 72),
            intArrayOf(503, 67), intArrayOf(503, 72), intArrayOf(505, 72), intArrayOf(505, 63), intArrayOf(505, 72),
            intArrayOf(507, 72), intArrayOf(507, 71), intArrayOf(507, 72), intArrayOf(509, 72), intArrayOf(509, 70),
            intArrayOf(509, 72), intArrayOf(511, 72), intArrayOf(511, 69), intArrayOf(511, 72)
        ),
        arrayOf(
            intArrayOf(497, 72), intArrayOf(497, 63), intArrayOf(497, 72), intArrayOf(499, 72), intArrayOf(499, 65),
            intArrayOf(499, 72), intArrayOf(501, 72), intArrayOf(501, 71), intArrayOf(501, 72), intArrayOf(503, 72),
            intArrayOf(503, 67), intArrayOf(503, 72), intArrayOf(505, 72), intArrayOf(505, 63), intArrayOf(505, 72),
            intArrayOf(507, 72), intArrayOf(507, 71), intArrayOf(507, 72), intArrayOf(509, 72), intArrayOf(509, 70),
            intArrayOf(509, 72), intArrayOf(511, 72), intArrayOf(511, 69), intArrayOf(511, 72)
        ),
        arrayOf(
            intArrayOf(523, 100), intArrayOf(523, 104), intArrayOf(519, 104), intArrayOf(519, 111),
            intArrayOf(528, 111), intArrayOf(528, 104), intArrayOf(526, 104), intArrayOf(526, 100)
        ),
        arrayOf(intArrayOf(513, 113), intArrayOf(513, 114), intArrayOf(527, 114)),
        arrayOf(
            intArrayOf(488, 59), intArrayOf(488, 51), intArrayOf(488, 59), intArrayOf(490, 59), intArrayOf(490, 52),
            intArrayOf(490, 59), intArrayOf(492, 59), intArrayOf(492, 55), intArrayOf(492, 59), intArrayOf(494, 59),
            intArrayOf(494, 52), intArrayOf(494, 59), intArrayOf(496, 59), intArrayOf(496, 50), intArrayOf(496, 59),
            intArrayOf(498, 59), intArrayOf(498, 58), intArrayOf(498, 59), intArrayOf(500, 59), intArrayOf(500, 56),
            intArrayOf(500, 59), intArrayOf(502, 59), intArrayOf(502, 56), intArrayOf(502, 59), intArrayOf(504, 59),
            intArrayOf(504, 57), intArrayOf(504, 59)
        ),
        arrayOf(
            intArrayOf(510, 75), intArrayOf(510, 78), intArrayOf(505, 78), intArrayOf(505, 84), intArrayOf(518, 84),
            intArrayOf(518, 78), intArrayOf(515, 78), intArrayOf(515, 75)
        ),
        arrayOf(
            intArrayOf(488, 59), intArrayOf(488, 51), intArrayOf(488, 59), intArrayOf(490, 59), intArrayOf(490, 52),
            intArrayOf(490, 59), intArrayOf(492, 59), intArrayOf(492, 55), intArrayOf(492, 59), intArrayOf(494, 59),
            intArrayOf(494, 52), intArrayOf(494, 59), intArrayOf(496, 59), intArrayOf(496, 50), intArrayOf(496, 59),
            intArrayOf(498, 59), intArrayOf(498, 58), intArrayOf(498, 59), intArrayOf(500, 59), intArrayOf(500, 56),
            intArrayOf(500, 59), intArrayOf(502, 59), intArrayOf(502, 56), intArrayOf(502, 59), intArrayOf(504, 59),
            intArrayOf(504, 57), intArrayOf(504, 59)
        ),
        arrayOf(intArrayOf(541, 152), intArrayOf(546, 152)),
        arrayOf(
            intArrayOf(523, 136), intArrayOf(523, 138), intArrayOf(518, 138), intArrayOf(518, 145),
            intArrayOf(535, 145), intArrayOf(535, 138), intArrayOf(528, 138), intArrayOf(528, 136)
        )
    )

    private fun drawRockFormationsAndGetMaxY(target: Array<CharArray>): Int {
        var maxY = 0
        for (formation in input) {
            val cur = formation[0].copyOf()
            maxY = maxOf(maxY, cur[1])
            target[cur[0]][cur[1]] = '#'
            for (i in 1 until formation.size) {
                val (x, y) = formation[i]
                maxY = maxOf(maxY, y)
                if (cur[0] == x) {
                    val d = (y - cur[1]).sign
                    while (cur[1] != y) {
                        cur[1] += d
                        target[cur[0]][cur[1]] = '#'
                    }
                } else {
                    val d = (x - cur[0]).sign
                    while (cur[0] != x) {
                        cur[0] += d
                        target[cur[0]][cur[1]] = '#'
                    }
                }
            }
        }
        return maxY
    }

    // time: O((h ^ 2) * w), space: O(h * w)
    override fun solveFirstPuzzle(): Int {
        val state = Array(560) { CharArray(170) { '.' } }
        val maxY = drawRockFormationsAndGetMaxY(state)
        var settled = 0
        while (true) {
            val cur = intArrayOf(500, 0)
            var isFalling = true
            while (isFalling) {
                if (cur[1] > maxY) return settled
                when {
                    state[cur[0]][cur[1] + 1] == '.' -> {
                    }
                    state[cur[0] - 1][cur[1] + 1] == '.' -> {
                        cur[0]--
                    }
                    state[cur[0] + 1][cur[1] + 1] == '.' -> {
                        cur[0]++
                    }
                    else -> {
                        state[cur[0]][cur[1]] = 'O'
                        settled++
                        isFalling = false
                    }
                }
                cur[1]++
            }
        }
    }

    // time: O((h ^ 2) * w), space: O(h * w)
    override fun solveSecondPuzzle(): Int {
        val state = Array(670) { CharArray(170) { '.' } }
        val floorY = drawRockFormationsAndGetMaxY(state) + 2
        for (i in state.indices) state[i][floorY] = '#'
        var settled = 0
        while (true) {
            val cur = intArrayOf(500, 0)
            var isFalling = true
            while (isFalling) {
                when {
                    state[cur[0]][cur[1] + 1] == '.' -> {
                    }
                    state[cur[0] - 1][cur[1] + 1] == '.' -> {
                        cur[0]--
                    }
                    state[cur[0] + 1][cur[1] + 1] == '.' -> {
                        cur[0]++
                    }
                    else -> {
                        state[cur[0]][cur[1]] = 'O'
                        settled++
                        if (cur[1] == 0) return settled
                        isFalling = false
                    }
                }
                cur[1]++
            }
        }
    }
}